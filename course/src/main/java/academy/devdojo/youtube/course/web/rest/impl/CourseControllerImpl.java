package academy.devdojo.youtube.course.web.rest.impl;

import academy.devdojo.youtube.core.domain.Course;
import academy.devdojo.youtube.course.service.CourseService;
import academy.devdojo.youtube.course.web.rest.CourseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CourseControllerImpl implements CourseController {

    private final CourseService courseService;

    @GetMapping(value = "/v1/admin/course", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Iterable<Course>> list(Pageable pageable) {
        log.info("list");
        log.info("pageable: " + pageable);

        return ResponseEntity.ok(courseService.list(pageable));
    }

}
