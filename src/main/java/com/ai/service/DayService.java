package com.ai.service;

import com.ai.entity.dto.SeqNum;
import com.ai.entity.dto.day.*;
import com.ai.entity.vo.request.CourseReqVO;
import com.ai.repo.dayRepo.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DayService {
    @Resource
    SeqNumRepository seqNumRepository;

    @Resource
    Day1Repository day1;
    @Resource
    Day2Repository day2;
    @Resource
    Day3Repository day3;
    @Resource
    Day4Repository day4;
    @Resource
    Day5Repository day5;
    @Resource
    Day6Repository day6;
    @Resource
    Day7Repository day7;


    public CourseReqVO getCourseTable(String uid) {
        SeqNum seqNum = seqNumRepository.findByUid(uid);
        int num = seqNum.getSeq_num();
        if (num == 0) {
            return null;
        }
        String[][] course = findCourse(uid, num);
        return new CourseReqVO(uid, course);
    }

    private String[][] findCourse(String uid, int num) {
        String[][] course = new String[num][7];
        for (int i = 1; i <= 7; i++) {
            String[] courseOneDay = findCourseOneDay(uid, i, num);
            for (int j = 0; j < num; j++) {
                course[j][i - 1] = courseOneDay[j];
            }
        }
        return course;
    }

    private String[] findCourseOneDay(String uid, int numDay, int num) {
        return switch (numDay) {
            case 1 -> {
                List<Day1> day1List = day1.findByUid(uid);
                yield day1List.stream()
                        .sorted(Comparator.comparingInt(Day1::getSeq))
                        .map(Day1::getCourse)
                        .toArray(String[]::new);
            }
            case 2 -> {
                List<Day2> day2List = day2.findByUid(uid);
                yield day2List.stream()
                        .sorted(Comparator.comparingInt(Day2::getSeq))
                        .map(Day2::getCourse)
                        .toArray(String[]::new);
            }
            case 3 -> {
                List<Day3> day3List = day3.findByUid(uid);
                yield day3List.stream()
                        .sorted(Comparator.comparingInt(Day3::getSeq))
                        .map(Day3::getCourse)
                        .toArray(String[]::new);
            }
            case 4 -> {
                List<Day4> day4List = day4.findByUid(uid);
                yield day4List.stream()
                        .sorted(Comparator.comparingInt(Day4::getSeq))
                        .map(Day4::getCourse)
                        .toArray(String[]::new);
            }
            case 5 -> {
                List<Day5> day5List = day5.findByUid(uid);
                yield day5List.stream()
                        .sorted(Comparator.comparingInt(Day5::getSeq))
                        .map(Day5::getCourse)
                        .toArray(String[]::new);
            }
            case 6 -> {
                List<Day6> day6List = day6.findByUid(uid);
                yield day6List.stream()
                        .sorted(Comparator.comparingInt(Day6::getSeq))
                        .map(Day6::getCourse)
                        .toArray(String[]::new);
            }
            case 7 -> {
                List<Day7> day7List = day7.findByUid(uid);
                yield day7List.stream()
                        .sorted(Comparator.comparingInt(Day7::getSeq))
                        .map(Day7::getCourse)
                        .toArray(String[]::new);
            }
            default -> new String[num];
        };

    }

    public void updateCourseTable(String uid, String[][] course, int num) {
        SeqNum seqNum = seqNumRepository.findByUid(uid);
        if (seqNum == null) {
            seqNumRepository.save(new SeqNum(uid, num));
        }else {
            seqNum.setSeq_num(num);
            seqNumRepository.save(seqNum);
        }
        String[][] reCourse = transpose(course);
        for (int i = 1; i <= 7; i++) {
            updateCourseOneDay(uid, reCourse[i - 1], i, num);
        }
    }

    private void updateCourseOneDay(String uid, String[] course, int day, int num) {
        switch (day) {
            case 1 -> {
                for (int i = 1; i <= num; i++) {
                    Optional<Day1> day1Query = day1.findByUidAndSeq(uid, i);
                    if (day1Query.isPresent()) {
                        Day1 day1Q = day1Query.get();
                        day1Q.setCourse(course[i - 1]);
                        day1.save(day1Q);
                    } else {
                        day1.save(new Day1(uid, course[i - 1], i));
                    }
                }
            }
            case 2 -> {
                for (int i = 1; i <= num; i++) {
                    Optional<Day2> day2Query = day2.findByUidAndSeq(uid, i);
                    if (day2Query.isPresent()) {
                        Day2 day2Q = day2Query.get();
                        day2Q.setCourse(course[i - 1]);
                        day2.save(day2Q);
                    } else {
                        day2.save(new Day2(uid, course[i - 1], i));
                    }
                }
            }
            case 3 -> {
                for (int i = 1; i <= num; i++) {
                    Optional<Day3> day3Query = day3.findByUidAndSeq(uid, i);
                    if (day3Query.isPresent()) {
                        Day3 day3Q = day3Query.get();
                        day3Q.setCourse(course[i - 1]);
                        day3.save(day3Q);
                    } else {
                        day3.save(new Day3(uid, course[i - 1], i));
                    }
                }
            }
            case 4 -> {
                for (int i = 1; i <= num; i++) {
                    Optional<Day4> day4Query = day4.findByUidAndSeq(uid, i);
                    if (day4Query.isPresent()) {
                        Day4 day4Q = day4Query.get();
                        day4Q.setCourse(course[i - 1]);
                        day4.save(day4Q);
                    } else {
                        day4.save(new Day4(uid, course[i - 1], i));
                    }
                }
            }
            case 5 -> {
                for (int i = 1; i <= num; i++) {
                    Optional<Day5> day5Query = day5.findByUidAndSeq(uid, i);
                    if (day5Query.isPresent()) {
                        Day5 day5Q = day5Query.get();
                        day5Q.setCourse(course[i - 1]);
                        day5.save(day5Q);
                    } else {
                        day5.save(new Day5(uid, course[i - 1], i));
                    }
                }
            }
            case 6 -> {
                for (int i = 1; i <= num; i++) {
                    Optional<Day6> day6Query = day6.findByUidAndSeq(uid, i);
                    if (day6Query.isPresent()) {
                        Day6 day6Q = day6Query.get();
                        day6Q.setCourse(course[i - 1]);
                        day6.save(day6Q);
                    } else {
                        day6.save(new Day6(uid, course[i - 1], i));
                    }
                }
            }
            case 7 -> {
                for (int i = 1; i <= num; i++) {
                    Optional<Day7> day7Query = day7.findByUidAndSeq(uid, i);
                    if (day7Query.isPresent()) {
                        Day7 day7Q = day7Query.get();
                        day7Q.setCourse(course[i - 1]);
                        day7.save(day7Q);
                    } else {
                        day7.save(new Day7(uid, course[i - 1], i));
                    }
                }
            }
        }
    }

    public static String[][] transpose(String[][] original) {
        int rows = original.length;
        int cols = original[0].length;

        String[][] transposed = new String[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = original[i][j];
            }
        }
        return transposed;
    }

}
