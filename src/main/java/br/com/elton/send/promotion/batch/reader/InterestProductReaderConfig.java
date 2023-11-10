package br.com.elton.send.promotion.batch.reader;

import br.com.elton.send.promotion.entity.ClientEntity;
import br.com.elton.send.promotion.entity.InterestProductClientEntity;
import br.com.elton.send.promotion.entity.ProductEntity;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class InterestProductReaderConfig {

    @StepScope
    @Bean
    public JdbcCursorItemReader<InterestProductClientEntity> interestProductReader(
            @Qualifier("appDataSource") DataSource dataSource) {

        return new JdbcCursorItemReaderBuilder<InterestProductClientEntity>()
                .name("interestProductReader")
                .dataSource(dataSource)
                .sql("select * from interest_product_client " +
                        "join client on (client = client.id) " +
                        "join product on (product = product.id) ")
                .rowMapper(rowMapper())
                .build();
    }

    private RowMapper<InterestProductClientEntity> rowMapper() {
        return new RowMapper<InterestProductClientEntity>() {

            @Override
            public InterestProductClientEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                ClientEntity client = new ClientEntity();
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setEmail(rs.getString("email"));

                ProductEntity product = new ProductEntity();
                product.setId(rs.getInt(6));
                product.setName(rs.getString(7));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));

                InterestProductClientEntity interestProductClient = new InterestProductClientEntity();
                interestProductClient.setClient(client);
                interestProductClient.setProduct(product);
                return interestProductClient;
            }
        };
    }

}
