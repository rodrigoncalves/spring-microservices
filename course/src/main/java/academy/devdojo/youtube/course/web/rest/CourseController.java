package academy.devdojo.youtube.course.web.rest;

import academy.devdojo.youtube.core.domain.Course;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@Api("Endpoints to manage course")
public interface CourseController {

    @ApiOperation(value = "List all available courses", response = Course[].class)
    ResponseEntity<Iterable<Course>> list(Pageable pageable);

}
