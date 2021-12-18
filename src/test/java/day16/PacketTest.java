package day16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacketTest {



    @Test
    void literalPacket(){
        Packet packet = new Packet("D2FE28");
        assertFalse(packet.isOperator());
        assertEquals(6, packet.getVersion());
        assertEquals(2021l, packet.getValue().longValue());
    }


    @Test
    void operatorPacketWithLengthTypeZero(){
        Packet packet = new Packet("38006F45291200");
        assertTrue(packet.isOperator());
        assertEquals(1, packet.getVersion());
        assertEquals(27,    packet.getSubPackagesLengt());
        assertEquals(2, packet.getSubpackages().size());
        assertEquals(10l, packet.getSubpackages().get(0).getValue().longValue());
        assertEquals(20l, packet.getSubpackages().get(1).getValue().longValue());
    }

    @Test
    void operatorPacketWithLengthTypeOne(){
        Packet packet = new Packet("EE00D40C823060");
        assertTrue(packet.isOperator());
        assertEquals(7, packet.getVersion());
        assertEquals(3,    packet.getNrOfSubPackages());
        assertEquals(3, packet.getSubpackages().size());
        assertEquals(1l, packet.getSubpackages().get(0).getValue().longValue());
        assertEquals(2l, packet.getSubpackages().get(1).getValue().longValue());
        assertEquals(3l, packet.getSubpackages().get(2).getValue().longValue());
    }

    @Test
    void sumOfVersions(){
        assertEquals(16, new Packet("8A004A801A8002F478").getSumOfVersionNumbers());
        assertEquals(12, new Packet("620080001611562C8802118E34").getSumOfVersionNumbers());
        assertEquals(23, new Packet("C0015000016115A2E0802F182340").getSumOfVersionNumbers());
        assertEquals(31, new Packet("A0016C880162017C3686B18A3D4780").getSumOfVersionNumbers());
    }

    @Test
    void getValue(){
        PacketDecoder decoder = new PacketDecoder();
        assertEquals(3l, new Packet("C200B40A82").getValue().longValue());
        assertEquals(54l, new Packet("04005AC33890").getValue().longValue());
        assertEquals(7l, new Packet("880086C3E88112").getValue().longValue());
        assertEquals(9l, new Packet("CE00C43D881120").getValue().longValue());
        assertEquals(1l, new Packet("D8005AC2A8F0").getValue().longValue());
        assertEquals(0l, new Packet("F600BC2D8F").getValue().longValue());
        assertEquals(0l, new Packet("9C005AC2F8F0").getValue().longValue());
        assertEquals(1l, new Packet("9C0141080250320F1802104A08").getValue().longValue());
    }

}