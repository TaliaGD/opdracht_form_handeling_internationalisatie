package be.ehb.opdracht_form_handeling_internationalisatie.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BlogpostDAO extends CrudRepository<Blogpost, Integer> {
    @Query("SELECT b FROM Blogpost b ORDER BY b.date DESC")
    Iterable <Blogpost> findAllChronological();
}

