//package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.valid;
//
//import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.CarService;
//import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.PersonService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//public class NotExistCarWithTheIdValidator implements ConstraintValidator<NotExistCarWithTheId, Long> {
//
////    private static final int ADULT_AGE = 18;
//    @Autowired
//    private CarService carService;
//
//
//    @Override
//    public void initialize(NotExistCarWithTheId notExistCarWithTheId) {
//
//    }
//
//    @Override
//    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
//        if (id != null) {
//            boolean exist = carService.existsById(id);
//            return ! exist;
//        } else {
//            return false;
//        }
//    }
//
//}
