package cn.edu.xaufe.demo.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import cn.edu.xaufe.demo.entity.Elder;
public interface ElderRepository extends JpaRepository<Elder, Long> {
    List<Elder> findByNursing(boolean isnursing);
    List<Elder> findByeidContaining(String eid);
}