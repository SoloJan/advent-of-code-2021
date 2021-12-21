package day16;

import lombok.Getter;
import util.StringUtil;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static util.StringUtil.hexToBinaryString;

@Getter
public class Packet {

    private int version;
    private int startIndex;
    private int type;
    private int endIndex;
    private char lengthType;
    private BigInteger value;
    private int nrOfSubPackages;
    private int subPackagesLengt;

    List<Packet> subpackages = new ArrayList<>();


    public Packet(String hexadecimalString){
        this(hexToBinaryString(hexadecimalString), 0);
    }

    public Packet(String binaryString, int startIndex){
        this.startIndex = startIndex;
        version =  Integer.valueOf(binaryString.substring(startIndex,startIndex+3), 2);
        type =  Integer.valueOf(binaryString.substring(startIndex+3, startIndex+6), 2);
        if(isLiteral()){
            setLiteralValue(binaryString);
        }
        else{
            lengthType =  binaryString.charAt(startIndex+6);
            if(lengthType == '0'){
                String subString = binaryString.substring(startIndex+7, startIndex+22);
                subPackagesLengt = Integer.valueOf(subString, 2);
                getSubPackagesForLengthTypeZero(binaryString);
            }
            else{
                String subString  = binaryString.substring(startIndex+7, startIndex+18);
                nrOfSubPackages = Integer.valueOf(subString, 2);
                getSubPackagesForLengthTypeOne(binaryString);
            }
            this.endIndex = subpackages.get(subpackages.size()-1).getEndIndex();
        }
    }

    private void getSubPackagesForLengthTypeZero(String binaryString){
        Packet subPacket = new Packet(binaryString, startIndex+ 22);
        subpackages.add(subPacket);
        int i = 0;
        while(true){
            if(subpackages.get(i).getEndIndex() >= startIndex+22+subPackagesLengt){
                return;
            }
            subpackages.add(new  Packet(binaryString, subpackages.get(i).getEndIndex()));
            i++;
        }
    }

    private void getSubPackagesForLengthTypeOne(String binaryString){
        Packet subPacket = new Packet(binaryString, startIndex+ 18);
        subpackages.add(subPacket);
        for(int i=1; i<nrOfSubPackages; i++){
            subpackages.add(new  Packet(binaryString, subpackages.get(i-1).getEndIndex()));
        }

    }

    private void setLiteralValue(String binaryString){
        boolean keepOnGoing = true;
        StringBuilder sb = new StringBuilder();
        int index = startIndex+6;
        while(keepOnGoing){
            if(binaryString.charAt(index) == '0'){
                keepOnGoing = false;
            }
            String toAppend = binaryString.substring(index+1, index+5);
            sb.append(toAppend);
            index += 5;
        }
        this.value = new BigInteger(sb.toString(), 2);
        this.endIndex = index;
    }

    public long  getSumOfVersionNumbers(){
        if(subpackages.isEmpty()){
            return version;
        }
        return version + subpackages.stream().map(p -> p.getSumOfVersionNumbers()).reduce(0L,Long::sum);
    }

    private boolean isLiteral(){
    return  type == 4;
    }

    public boolean isOperator(){
        return type !=4;
    }

    public BigInteger getValue(){
        switch (type) {
            case 0:
                return subpackages.stream().map(Packet::getValue).reduce(BigInteger.ZERO, BigInteger::add);
            case 1:
                BigInteger product = BigInteger.ONE;
                for (Packet packet : subpackages) {
                    product = product.multiply(packet.getValue());
                }
                return product;
            case 2:
                return subpackages.stream().map(Packet::getValue).reduce(BigInteger::min).orElseThrow();
            case 3:
                return subpackages.stream().map(Packet::getValue).reduce(BigInteger.ZERO, BigInteger::max);
            case 4:
                return value;
            case 5:
                return subpackages.get(0).getValue().compareTo(subpackages.get(1).getValue()) == 1 ? BigInteger.ONE : BigInteger.ZERO;
            case 6:
                return subpackages.get(0).getValue().compareTo(subpackages.get(1).getValue()) == -1 ? BigInteger.ONE : BigInteger.ZERO;
            case 7:
                return subpackages.get(0).getValue().compareTo(subpackages.get(1).getValue()) == 0 ? BigInteger.ONE : BigInteger.ZERO;
        }
        return value;
    }

}
