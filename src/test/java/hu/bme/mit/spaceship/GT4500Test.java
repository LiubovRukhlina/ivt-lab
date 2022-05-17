package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test{

  private GT4500 ship;
  private TorpedoStore MockTS1, MockTS2;

  @BeforeEach
  public void init(){
    this.MockTS1 = mock(TorpedoStore.class);
    this.MockTS2 = mock(TorpedoStore.class);
    this.ship = new GT4500(MockTS1, MockTS2);
   
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(MockTS1.fire(1)).thenReturn(true);
    when(MockTS2.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert

    verify(MockTS1, times(1)).fire(1);
    verify(MockTS2, times(0)).fire(1);
 
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(MockTS1.fire(1)).thenReturn(true);
    when(MockTS2.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(MockTS1, times(1)).fire(1);
    verify(MockTS2, times(1)).fire(1);
  }

  @Test
  public void fireBothEmpty(){
    // Arrange
    when(MockTS1.isEmpty()).thenReturn(true);
    when(MockTS2.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(MockTS1, times(0)).fire(1);
    verify(MockTS2, times(0)).fire(1);
  }

  @Test
  public void fireFirstEmpty(){
    // Arrange
    when(MockTS1.isEmpty()).thenReturn(true);
    when(MockTS2.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(MockTS1, times(0)).fire(1);
    verify(MockTS2, times(1)).fire(1);
  }

  
  @Test
  public void fireAlternating(){
    // Arrange
    when(MockTS1.fire(1)).thenReturn(true);
    when(MockTS2.fire(1)).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(MockTS1, times(1)).fire(1);
    verify(MockTS2, times(1)).fire(1);
  }
  @Test
  public void fireFirstTwice(){
    // Arrange git config --global user.name "
    when(MockTS1.fire(1)).thenReturn(true);
    when(MockTS2.isEmpty()).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(MockTS1, times(2)).fire(1);

  }


}
