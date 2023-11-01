package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.entity.Employee;
import com.example.be_adm_double_shop.repository.EmployeeRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private static final int PASSWORD_LENGTH = 8;

    // Hàm sinh mật khẩu ngẫu nhiên
    private String generateRandomPassword() {
        return RandomStringUtils.randomAlphanumeric(PASSWORD_LENGTH);
    }

    private final EmployeeRepository employeeRepository;
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, JavaMailSender javaMailSender) {
        this.employeeRepository = employeeRepository;
        this.javaMailSender = javaMailSender;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Employee saveEmployee(Employee employee) {
        // Lấy ID của người trước đó
        Long previousEmployeeId = employeeRepository.findMaxId();

        // Thiết lập mã nhân viên mặc định là "NV" cộng với ID của người trước đó
        String employeeCode = "NV" + (previousEmployeeId != null ? previousEmployeeId + 1 : 1);
        employee.setCode(employeeCode);

        // Tạo mật khẩu ngẫu nhiên
        String randomPassword = generateRandomPassword();
        employee.setPassword(randomPassword);

        // Lưu nhân viên vào cơ sở dữ liệu và lấy ra để có được đối tượng đã được lưu vào DB
        Employee savedEmployee = employeeRepository.save(employee);
        employeeRepository.flush(); // Đảm bảo rằng thay đổi được lưu vào DB ngay lập tức

        // Gửi email thông báo sau khi thêm nhân viên mới
        sendNewEmployeeNotification(savedEmployee);

        return savedEmployee;
    }
    private void sendNewEmployeeNotification(Employee employee) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(employee.getEmail());
        message.setSubject("Chào mừng bạn đến với công ty chúng tôi!");

        // Sửa nội dung email để chứa thông tin tài khoản và mật khẩu
        String emailContent = "Chúc mừng " + employee.getName() + "! Bạn đã trở thành một phần của đội ngũ nhân viên của chúng tôi.\n\n"
                + "Dưới đây là thông tin tài khoản của bạn để phục vụ việc bán hàng:\n"
                + "Tài khoản: " + employee.getEmail() + "\n"
                + "Mật khẩu: " + employee.getPassword(); // Giả sử employee.getPhone() trả về số điện thoại, và employee.getCode() trả về mã nhân viên
        message.setText(emailContent);

        javaMailSender.send(message);
    }
    public Page getAllByPage(int page, int pageSize) {
        Pageable p = PageRequest.of(page, pageSize);
        return employeeRepository.findAll(p);
    }


    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }


    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setName(employee.getName());
            existingEmployee.setGender(employee.getGender());
            existingEmployee.setPhone(employee.getPhone());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setPassword(employee.getPassword());
            existingEmployee.setDistrict(employee.getDistrict());
            existingEmployee.setProvice(employee.getProvice());
            existingEmployee.setCity(employee.getCity());
            existingEmployee.setGender(employee.getGender());
            existingEmployee.setBirthDay(employee.getBirthDay());
            existingEmployee.setRole(employee.getRole());
            existingEmployee.setStatus(employee.getStatus());

            // Thiết lập giá trị cho các trường updated_by và updated_time
            existingEmployee.setUpdatedBy(id); // Điền vào ID của người đang đăng nhập
            existingEmployee.setUpdatedTime(LocalDateTime.now());

            return employeeRepository.save(existingEmployee);
        }
        return null;
    }
    public List<Employee> searchEmployees(String keyword) {
        return employeeRepository.findByCodeContainingIgnoreCaseOrNameContainingIgnoreCase(keyword, keyword);
    }

    public List<Employee> sapXepTheoCode() {
        return employeeRepository.findAllByOrderByCode();
    }
}
