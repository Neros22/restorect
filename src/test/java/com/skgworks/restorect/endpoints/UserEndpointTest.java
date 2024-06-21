package com.skgworks.restorect.endpoints;

import com.skgworks.restorect.dto.UserDto;
import com.skgworks.restorect.model.User;
import com.skgworks.restorect.repository.UserRepository;
import com.skgworks.restorect.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class UserEndpointTest {


}