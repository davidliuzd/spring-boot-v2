package net.liuzd.spring.boot.v2.mappper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.liuzd.spring.boot.v2.domain.Hotel;
import net.liuzd.spring.boot.v2.mapper.HotelMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelMapperTest {

  @Autowired
  private HotelMapper hotelMapper;

  @Test
  public void selectByCityIdTest() {
    Hotel hotel = hotelMapper.selectByCityId(1);
    assertThat(hotel.getName()).isEqualTo("Conrad Treasury Place");
    assertThat(hotel.getAddress()).isEqualTo("William & George Streets");
    assertThat(hotel.getZip()).isEqualTo("4001");
  }

}
