package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.entity.Customer;
import com.example.be_adm_double_shop.entity.Rank;
import com.example.be_adm_double_shop.repository.CustomerRepository;
import com.example.be_adm_double_shop.repository.RankRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RankRepository rankRepository;

    private final JavaMailSender javaMailSender;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, JavaMailSender javaMailSender) {
        this.customerRepository = customerRepository;
        this.javaMailSender = javaMailSender;
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Customer save(Customer customer) {
        // Thiết lập giá trị mặc định cho rank nếu không được thiết lập
        if (customer.getRank() == null) {
            // Lấy rank thấp nhất từ cơ sở dữ liệu
            Rank lowestRank = rankRepository.findFirstByOrderBySinceAsc();
            customer.setRank(lowestRank);
        }
        // Lưu khách hàng vào cơ sở dữ liệu và lấy ra để có được đối tượng đã được lưu vào DB
        Customer savedCustomer = customerRepository.save(customer);
        customerRepository.flush(); // Đảm bảo rằng thay đổi được lưu vào DB ngay lập tức

        // Gửi email chào mừng và giới thiệu rank sau khi đăng ký thành công
        sendWelcomeEmail(savedCustomer);

        return savedCustomer;
    }
    private void sendWelcomeEmail(Customer customer) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customer.getEmail());
        message.setSubject("Chào mừng bạn đến với cửa hàng chúng tôi!");

        // Sửa nội dung email để chứa thông tin tài khoản và mật khẩu
        String emailContent = "Chào mừng " + customer.getName() + "! Bạn đã đăng ký thành công tài khoản tại cửa hàng của chúng tôi.\n\n"
                + "Dưới đây là thông tin tài khoản của bạn:\n"
                + "Tài khoản: " + customer.getEmail() + "\n"
                + "Mật khẩu: " + customer.getPassword() + "\n\n"
                + "Chúng tôi rất vui được chào đón bạn. Hãy khám phá các sản phẩm và ưu đãi tuyệt vời của chúng tôi!";
        message.setText(emailContent);

        javaMailSender.send(message);
    }

    public Customer findById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElse(null); // Returns null if customer is not found
    }
    public Customer updateCustomer(Long id, Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            existingCustomer.setUsername(customer.getUsername());
            existingCustomer.setName(customer.getName());
            existingCustomer.setGender(customer.getGender());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPassword(customer.getPassword());
            existingCustomer.setBirtDay(customer.getBirtDay());
            existingCustomer.setStatus(customer.getStatus());
            existingCustomer.setCreatedBy(customer.getCreatedBy());
            existingCustomer.setUpdated_by(customer.getUpdated_by());
            existingCustomer.setCreatedTime(customer.getCreatedTime());
            // Set other properties of Customer as needed

            return customerRepository.save(existingCustomer);
        }
        // Handle not found case if needed, such as throwing an exception
        return null;
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public List<Customer> searchCustomer(String keyword) {
        return customerRepository.findByUsernameContainingIgnoreCase(keyword);
    }

    public List<Customer> sapXep() {
        return customerRepository.findAllByOrderByUsername();
    }
}