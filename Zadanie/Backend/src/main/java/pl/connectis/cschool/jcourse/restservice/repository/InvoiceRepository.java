package pl.connectis.cschool.jcourse.restservice.repository;

import org.springframework.data.repository.CrudRepository;
import pl.connectis.cschool.jcourse.restservice.domain.Invoice;
import pl.connectis.cschool.jcourse.restservice.domain.Product;

import java.util.List;

/**
 * Created by Benia on 2017-06-24.
 */
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}
