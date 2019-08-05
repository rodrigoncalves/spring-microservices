package academy.devdojo.youtube.course.service;

import academy.devdojo.youtube.core.domain.Course;
import academy.devdojo.youtube.core.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public Iterable<Course> list(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }
}
