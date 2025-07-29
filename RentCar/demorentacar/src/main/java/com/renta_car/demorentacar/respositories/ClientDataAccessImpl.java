package com.renta_car.demorentacar.respositories;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.renta_car.demorentacar.models.Client;
import com.renta_car.demorentacar.services.ClientDAO;

import com.renta_car.demorentacar.utilitys.ClientRowMapper;;


@Repository
public class ClientDataAccessImpl  implements ClientDAO {

    //Implementamos jdbc para obtener los datos de la base de datos

    private final JdbcTemplate jdbcTemplate;
    
    //Inyectamos

    public ClientDataAccessImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

  

    @Override
    public List<Client> selectClientes() {
        //Realizamos la seleccion de los datos
        String sql = "SELECT * FROM cliente";
        return jdbcTemplate.query(sql,new ClientRowMapper());
    }

  

    @Override
    public Optional<Client> selectClienteByid(int id) {
        String sql = "SELECT id_cliente, nombre, direccion, email, telefono, nickname, password FROM cliente WHERE id_cliente = ?";
        List<Client> clientes = jdbcTemplate.query(sql, new ClientRowMapper(), id);
        //Al ser ids diferentes no hace falta hacer esto pero po si acaso
        //retornamos. recorremos y encontramos el primeroTODO ESTO SON BUENAS PRACTICAS
        
        return clientes.stream().findFirst();
    }



    @Override
    public int deleteClient(Integer id) {
        //Realizamos el sql
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        return jdbcTemplate.update(sql, id);
    }


    @Override
    public Optional<Client> selectClienteByEmail(String email) {

        //Planteamos el Sql

        String sql = "SELECT id_cliente, nombre, direccion, email, telefono, nickname, password FROM cliente WHERE email = ?";
        //Podria retornar todo pero para eso mejor hago la busqueda eficiente
        List<Client> clientes = jdbcTemplate.query(sql, new ClientRowMapper(), email);

        //eficiencia al usar estrem con find
        return clientes.stream().findFirst();
    }



    @Override
    public int insertCliente(Client cliente) {
        String sql = """
            INSERT INTO cliente(nombre, direccion, email, telefono, nickname, password)
            VALUES (?,?,?,?,?,?)
                """;

        return jdbcTemplate.update(sql,
            cliente.getName(),
            cliente.getDireccion(),
            cliente.getEmail(),
            cliente.getPhone(),
            cliente.getNickname(),
            cliente.getPassword()
        );
    }



    @Override
    public int updateCliente(Client cliente) {
        String sql = """
                UPDATE cliente SET nombre = ?, direccion = ?, email = ?, telefono = ?, nickname = ?, password = ? WHERE id_cliente = ?
                """;

        return jdbcTemplate.update(sql,
        cliente.getName(),
        cliente.getDireccion(),
        cliente.getEmail(),
        cliente.getPhone(),
        cliente.getNickname(),
        cliente.getPassword(),
        cliente.getIdCliente()
         );        
    }

}
