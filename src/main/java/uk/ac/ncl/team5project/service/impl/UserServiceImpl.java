package uk.ac.ncl.team5project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uk.ac.ncl.team5project.entity.Admin;
import uk.ac.ncl.team5project.entity.Book;
import uk.ac.ncl.team5project.entity.User;
import uk.ac.ncl.team5project.entity.Wishlist;
import uk.ac.ncl.team5project.mapper.AdminMapper;
import uk.ac.ncl.team5project.mapper.UserMapper;
import uk.ac.ncl.team5project.mapper.WishlistMapper;
import uk.ac.ncl.team5project.model.dto.LoginDTO;
import uk.ac.ncl.team5project.model.dto.RegisterDTO;
import uk.ac.ncl.team5project.model.vo.LoginVO;
import uk.ac.ncl.team5project.model.vo.RegisterVO;
import uk.ac.ncl.team5project.model.vo.UpdateVO;
import uk.ac.ncl.team5project.model.vo.UserInfoVO;
import uk.ac.ncl.team5project.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import uk.ac.ncl.team5project.util.Constants;
import uk.ac.ncl.team5project.util.JwtUtil;
import uk.ac.ncl.team5project.util.Result;

import java.util.List;

/**
 * @file UserServiceImpl.java
 * @date 2025-04-01
 * @function_description: Service implementation for user registration, login, admin login, profile update and info retrieval.
 * @interface_description: register, login, adminLogin, update, getInfo
 * @calling_sequence: Controller → Service → Mapper → Database
 * @arguments_description: RegisterDTO, LoginDTO, String username/password
 * @list_of_subordinate_classes: JwtUtil, AdminMapper, WishlistMapper
 * @discussion: All endpoints require JWT authentication except register and login.
 * @development_history: Created on 2025-04-01 as part of user module
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Handles user business logic.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private WishlistMapper wishlistMapper;
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Result<?> register(RegisterDTO user) {

        // Check if any required parameter is missing
        if (user == null || user.getUsername() == null
                || user.getPassword() == null || user.getEmail() == null) {
            return Result.error(500, "Invalid parameters");
        }
        // Check if the email is already registered
        User existingUser = baseMapper.selectOne(new QueryWrapper<User>().eq("user_email", user.getEmail()));
        if (existingUser != null) {
            return Result.error(500, "User already exists");
        }

        // Create a new user object and populate fields
        User newUser = new User();
        newUser.setUserEmail(user.getEmail());
        newUser.setUserName(user.getUsername());
        newUser.setPassword(user.getPassword());
        // Save the new user to the database
        boolean save = save(newUser);
        if (save) {
            RegisterVO registerVO = new RegisterVO();
            registerVO.setId(newUser.getUserId());
            registerVO.setUsername(newUser.getUserName());
            registerVO.setEmail(newUser.getUserEmail());

            return Result.success(registerVO);
        }

        return Result.error(500, "Registration failed");

    }

    @Override
    public Result<?> login(LoginDTO user) {
        // Check if login input is valid
        if (user == null || user.getEmail() == null
                || user.getPassword() == null) {
            return Result.error(500, "Invalid parameters");
        }
        // Find user by email
        User existingUser = baseMapper.selectOne(new QueryWrapper<User>().eq("user_email", user.getEmail()));
        if (existingUser == null) {
            return Result.error(500, "User not found");
        }
        // Check if password matches
        if (!existingUser.getPassword().equals(user.getPassword())) {
            return Result.error(500, "Incorrect password");
        }
        // Generate token and return login success
        String token = jwtUtil.generateJwtToken(user.getEmail(), Constants.USER_ROLE);
        LoginVO loginVO = new LoginVO();
        loginVO.setToken(token);
        loginVO.setUsername(existingUser.getUserName());
        loginVO.setUserId(existingUser.getUserId());
        return Result.success(loginVO);
    }

    @Override
    public Result<?> adminLogin(LoginDTO user) {
        // Validate input parameters
        if (user == null || user.getEmail() == null
                || user.getPassword() == null) {
            return Result.error(500, "Invalid parameters");
        }
        // Find admin by email
        Admin existingUser = adminMapper.selectOne(new QueryWrapper<Admin>().eq("admin_email", user.getEmail()));
        if (existingUser == null) {
            return Result.error(500, "Admin not found");
        }
        // Validate password
        if (!existingUser.getPassword().equals(user.getPassword())) {
            return Result.error(500, "Incorrect password");
        }
        // Return token if login successful
        String token = jwtUtil.generateJwtToken(user.getEmail(), Constants.USER_ROLE);
        LoginVO loginVO = new LoginVO();
        loginVO.setToken(token);
        loginVO.setUsername(existingUser.getAdminName());
        loginVO.setUserId(existingUser.getAdminId());
        return Result.success(loginVO);
    }

    @Override
    public Result<?> update(String userName, String password) {
        // Get current user from JWT token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("user_email", email));
        if (user == null) {
            return Result.error(500, "User not found");
        }
        // Update username and/or password if provided
        if (userName != null) {
            user.setUserName(userName);
        }
        if (password != null) {
            user.setPassword(password);
        }
        // Save updated user
        boolean update = updateById(user);
        if (update) {
            UpdateVO updateVO = new UpdateVO();
            updateVO.setUser_id(user.getUserId());
            updateVO.setUser_name(user.getUserName());
            updateVO.setUser_email(user.getUserEmail());
            updateVO.setMessage("User information updated successfully");
            return Result.success(updateVO);
        }
        return Result.error(500, "Update failed");
    }

    @Override
    public Result<?> getInfo() {
        // Get authenticated user's email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("user_email", email));
        if (user == null) {
            return Result.error(500, "User not found");
        }
        // Build user info response object
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUserId(user.getUserId());
        userInfoVO.setUserName(user.getUserName());
        userInfoVO.setUserEmail(user.getUserEmail());

        // Get user's wishlist books
        List<Wishlist> wishlists = wishlistMapper.selectList(
                new QueryWrapper<Wishlist>().eq("user_id", user.getUserId()));

        List<Book> wishlistBooks = wishlists.stream()
                .map((wishlist) -> {
                    Book book = new Book();
                    book.setBookId(wishlist.getBookId());
                    return book;
                })
                .toList();

        userInfoVO.setBorrowedCount(0);
        userInfoVO.setBorrowedBooks(null);
        userInfoVO.setWishlist(wishlistBooks);
        return Result.success(userInfoVO);

    }
}
