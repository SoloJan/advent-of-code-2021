package day19;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Beacon {

    public Beacon(String coordinateString){
        String[] coordinates = coordinateString.split(",");
        x = Integer.valueOf(coordinates[0]);
        y = Integer.valueOf(coordinates[1]);
        z = Integer.valueOf(coordinates[2]);
    }

    int x;
    int y;
    int z;


    public void mapToZeroScanner(MapToZeroScanner xMap, MapToZeroScanner yMap, MapToZeroScanner zMap){
        int oldX = x;
        int oldY = y;
        int oldZ = z;
        mapX(xMap, oldX, oldY, oldZ);
        mapY(yMap, oldX, oldY, oldZ);
        mapZ(zMap, oldX, oldY, oldZ);

    }

    public void mapX(MapToZeroScanner xMap, int oldX, int oldY, int oldZ){
        switch (xMap.getRotation()){
            case XX:
                x = oldX+ xMap.getMagnitude();
                return;
            case XMINUSX:
                x = (-1*oldX) + xMap.getMagnitude();
                return;
            case XY:
                x = oldY + xMap.getMagnitude();
                return;
            case XMINUSY:
                x = (-1*oldY) + xMap.getMagnitude();
                return;
            case XZ:
                x = oldZ + xMap.getMagnitude();
                return;
            case XMINUSZ:
                x = (-1*oldZ) + xMap.getMagnitude();
                return;
        }
    }


    public void mapY(MapToZeroScanner yMap, int oldX, int oldY, int oldZ){
        switch (yMap.getRotation()){
            case YX:
                y = oldX+ yMap.getMagnitude();
                return;
            case YMINUSX:
                y = (-1*oldX) + yMap.getMagnitude();
                return;
            case YY:
                y = oldY + yMap.getMagnitude();
                return;
            case YMINUSY:
                y = (-1*oldY) + yMap.getMagnitude();
                return;
            case YZ:
                y = oldZ + yMap.getMagnitude();
                return;
            case YMINUSZ:
                y = (-1*oldZ) + yMap.getMagnitude();
                return;
        }
    }

    public void mapZ(MapToZeroScanner zMap, int oldX, int oldY, int oldZ){
        switch (zMap.getRotation()){
            case ZX:
                z = oldX+ zMap.getMagnitude();
                return;
            case ZMINUSX:
                z = (-1*oldX) + zMap.getMagnitude();
                return;
            case ZY:
                z = oldY + zMap.getMagnitude();
                return;
            case ZMINUSY:
                z = (-1*oldY) + zMap.getMagnitude();
                return;
            case ZZ:
                z = oldZ + zMap.getMagnitude();
                return;
            case ZMINUSZ:
                z = (-1*oldZ) + zMap.getMagnitude();
                return;
        }
    }


}
