package cn.edu.xaufe.demo.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.edu.xaufe.demo.entity.Elder;
import cn.edu.xaufe.demo.repository.ElderRepository;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@EnableAutoConfiguration
@RequestMapping("/api")
public class ElderController {
    @Autowired
    ElderRepository elderRepository;
    @GetMapping("/elders")
    public ResponseEntity<List<Elder>> getAllElder(@RequestParam(required = false) String eid) {
        try {
            List<Elder> elders = new ArrayList<Elder>();
            if (eid == null)
                elderRepository.findAll().forEach(elders::add);
            else
                elderRepository.findByeidContaining(eid).forEach(elders::add);
            if (elders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(elders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/elders/{id}")
    public ResponseEntity<Elder> getElderById(@PathVariable("id") long id) {
        Optional<Elder> elderData = elderRepository.findById(id);
        if (elderData.isPresent()) {
            return new ResponseEntity<>(elderData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/elders")      //post是添加新值
    public ResponseEntity<Elder> createElder(@RequestBody Elder elder) {
        try {
            Elder _elder = elderRepository
                    .save(new Elder(elder.getEid(), elder.getNursinghome(),
                            elder.getName(), elder.getSex(), elder.getAge(),
                            elder.getPhonenum(), elder.getRoomid(), elder.getAddress(), false));
            return new ResponseEntity<>(_elder, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/elders/{id}")      //put是覆盖旧值
    public ResponseEntity<Elder> updateElder(@PathVariable("id") long id, @RequestBody Elder elder) {
        Optional<Elder> elderData = elderRepository.findById(id);
        if (elderData.isPresent()) {
            Elder _elder = elderData.get();
            _elder.setEid(elder.getEid());
            _elder.setNursinghome(elder.getNursinghome());
            _elder.setNursing(elder.isNursing());
            _elder.setName(elder.getName());
            _elder.setSex(elder.getSex());
            _elder.setRoomid(elder.getRoomid());
            _elder.setPhonenum(elder.getPhonenum());
            _elder.setAge(elder.getAge());
            _elder.setNursinghome(elder.getNursinghome());
            return new ResponseEntity<>(elderRepository.save(_elder), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/elders/{id}")
    public ResponseEntity<HttpStatus> deleteElder(@PathVariable("id") long id) {
        try {
            elderRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/elders")
    public ResponseEntity<HttpStatus> deleteAllElders() {
        try {
            elderRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/elders/nursing")
    public ResponseEntity<List<Elder>> findByNursing() {
        try {
            List<Elder> elders = elderRepository.findByNursing(true);
            if (elders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(elders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
