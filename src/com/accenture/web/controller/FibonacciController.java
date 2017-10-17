package com.accenture.web.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.accenture.web.service.impl.Fibonacci;

@Controller
public class FibonacciController {
	private static final Logger logger = Logger.getLogger(FibonacciController.class);
	private static final String POSITIVE_NUMBER_REGEX = "^\\d+$";
	
	@RequestMapping("/FibonacciController")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");

		String lengt;
		lengt = request.getParameter("length");

		Fibonacci fibonacci = new Fibonacci();

		List<BigInteger> value = new ArrayList<>();
		
		if (lengt.matches(POSITIVE_NUMBER_REGEX)) {
			for (int i = 0; i < Integer.parseInt(lengt); i++) {
				
				if (i <= 92) {
					
					Long val = fibonacci.fibonacciFunction(i);
					BigInteger big = new BigInteger(val.toString());
					value.add(big);
					
				} else {
					
					value.add(fibonacci.fibonacciBigN(i));
				}
			}
			request.getSession().setAttribute("sequence", value);
			request.getRequestDispatcher("/ResponseFibonacci.jsp").forward(request, response);
			
		} else {
			
			request.setAttribute("error","Error:please input a positive number!");
			request.getRequestDispatcher("/FibonacciError.jsp").forward(request, response);
			logger.info("Please input a number!");
		}

	}
}
