package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.print.DocFlavor;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	//main 메소드는 spring이 관리하지 않는다.
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// DataSource Bean(Spring이 관리하는 객체)
	@Autowired //자동으로 주입
	DataSource dataSource;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("스프링 부트가 관리하는 빈을 사용할 수 있다.");

		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select role_id, name from role");
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()){
			int roleId = resultSet.getInt("role_id");
			String name = resultSet.getString("name");
			System.out.println(roleId+","+name);
		}
		resultSet.close();
		preparedStatement.close();
		connection.close();
	}
}
