package org.tftp.packets;


import java.nio.ByteBuffer;

/*

03|<Block#>|<Data>
 */
public class DataPacket extends Packet{
    private final int blockNumber;

    private final boolean isLastPacket;
    private final byte[] data;
    public DataPacket(ByteBuffer buffer){
        int totalLength = buffer.limit();
        //fill up block number
        buffer.position(2);
        byte[] blockBytes = new byte[4];
        buffer.get(blockBytes, 2, 2);
        blockNumber = ByteBuffer.wrap(blockBytes).getInt();
        //fill up data
        data = new byte[totalLength - 4];
        buffer.get(data, 0, data.length);
        isLastPacket = data.length != 512;
    }

    @Override
    public int getOpcode(){
        return 3;
    }

    public boolean isLastPacket() {
        return isLastPacket;
    }

    public byte[] getData() {
        return data;
    }

    public int getBlockNumber() {
        return blockNumber;
    }

}
