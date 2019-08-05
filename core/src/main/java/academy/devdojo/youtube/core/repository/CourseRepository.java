package academy.devdojo.youtube.core.repository;

import academy.devdojo.youtube.core.domain.Course;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
}
