package com.natalia.otes12;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class UnionCorporation {

    public static void main(String[] args){

        ServerResponse firstEmployee = getEmployee("1");
        ServerResponse secondEmployee = getEmployee("2");

        List<Funcionario> funcionarioList = new ArrayList<>();

        Funcionario funcionario1 = new Funcionario(firstEmployee.getName(), firstEmployee.getAddress());
        funcionario1.setCota("2020/2", 20.0, 10.0);

        Funcionario funcionario2 = new Funcionario(secondEmployee.getName(), secondEmployee.getAddress());
        funcionario2.setCota("2020/2", 45.0, 45.0);

        funcionarioList.add(funcionario1);
        funcionarioList.add(funcionario2);

        funcionarioList.forEach(funcionario -> funcionario.imprimirRelatorio());

        funcionario1.updateProgresso(20.0);

        funcionarioList.forEach(funcionario -> funcionario.imprimirRelatorio());
    }

    private static ServerResponse getEmployee(String userId) {
        final String uri = "http://localhost:8000/employee?userId=" + userId;

        RestTemplate restTemplate = new RestTemplate();
        ServerResponse result = restTemplate.getForObject(uri, ServerResponse.class);

        return result;
    }

    /* CÓDIGO VULNERÁVEL
    private static ServerResponse getEmployee(String email) {
        final String uri = "http://localhost:8000/employee?email=" + email;

        RestTemplate restTemplate = new RestTemplate();
        ServerResponse result = restTemplate.getForObject(uri, ServerResponse.class);

        return result;
    } */

}