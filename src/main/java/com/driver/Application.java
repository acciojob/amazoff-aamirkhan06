package com.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		// Errors:
		//Error:    TestCases.testAddOrder:25 » NullPointer
		//Error:    TestCases.testAddOrderPartnerPair:53 » NullPointer
		//Error:    TestCases.testAddPartner:34 » NullPointer
		//Error:    TestCases.testDeletePartnerById:639 » NullPointer
		//Error:    TestCases.testGetAllOrders:319 » NullPointer
		//Error:    TestCases.testGetCountOfOrdersLeftAfterGivenTime:531 » NullPointer
		//Error:    TestCases.testGetCountOfUnassignedOrders:389 » NullPointer
		//Error:    TestCases.testGetLastDeliveryTime:593 » NullPointer
		//Error:    TestCases.testGetOrderById:88 » NullPointer
		//Error:    TestCases.testGetOrderCountByPartnerId:193 » NullPointer
		//Error:    TestCases.testGetOrdersByPartnerId:239 » NullPointer
		//Error:    TestCases.testGetPartnerById:142 » NullPointer
		//Error:    TestCases.testOrderController:684 » NullPointer
	}
}
