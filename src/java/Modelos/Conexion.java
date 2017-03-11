
package Modelos;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Conexion {
    
    public DriverManagerDataSource conectar() 
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://rdstest.cauxrp6umjn5.sa-east-1.rds.amazonaws.com:3306/Minugest");
        dataSource.setUsername("admin");
        dataSource.setPassword("78547854");
        return dataSource;
       
    }
}
