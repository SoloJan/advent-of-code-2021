package day16;


import static util.FileUtil.readFilePerLine;

public class PacketDecoder {

    public long getSumOfVersionNumbers(String fileName){
      return  new Packet(readFilePerLine(fileName).get(0)).getSumOfVersionNumbers();
    }

    public long getValue(String fileName){
        return  decodePacket(readFilePerLine(fileName).get(0)).getValue().longValue();
    }

    protected Packet decodePacket(String s){
        return new Packet(s);
    }

}
