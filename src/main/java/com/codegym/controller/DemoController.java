package com.codegym.controller;

import com.codegym.exception.CustomerNotFound;
import com.codegym.model.Customer;
import com.codegym.model.CustomerForm;
import com.codegym.model.Province;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/demo")
@PropertySource("classpath:upload_file.properties")
public class DemoController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IProvinceService provinceService;

    @Value("${file-upload}")
    private String fileUpload;

    @ExceptionHandler(CustomerNotFound.class)
    public ModelAndView showNotFound(){
        return new ModelAndView("notFound");
    }

//    Lấy ra danh sách tỉnh
    @ModelAttribute("dstinh")
    public Iterable<Province> provinces(){
        return provinceService.findAll();
    }

    @GetMapping()
    public ModelAndView demo(){
        Iterable<Customer> customers = customerService.findAll();
        return new ModelAndView("home", "customers", customers);
    }

    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("customer", new CustomerForm());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createNewCustomer(@Validated @ModelAttribute("customer") CustomerForm customerForm, BindingResult bindingResult){
        if (!bindingResult.hasErrors()){
            //Lấy file ảnh
            MultipartFile file = customerForm.getAvatar();
            //lấy tên file
            String fileName = file.getOriginalFilename();
            //Lấy thông tin của customer
            String firstName = customerForm.getFirstName();
            String lastName = customerForm.getLastName();
            Province province = customerForm.getProvince();
            //Copy File
            try {
                FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Gán lại đối tượng cho customer
            Customer customer = new Customer(firstName, lastName, fileName, province);
            //Lưu đối tượng customer
            ModelAndView modelAndView = new ModelAndView("create");
            customerService.save(customer);
            modelAndView.addObject("mess", "Create customer successfully");
            modelAndView.addObject("customers", customer);
            return modelAndView;
        }
        return new ModelAndView("create");
    }

    @GetMapping("/{id}")
    public ModelAndView details(@PathVariable Long id) throws CustomerNotFound {
        ModelAndView modelAndView = new ModelAndView("details");
        Customer customer = customerService.findById(id).get();
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }


}
