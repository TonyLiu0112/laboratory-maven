package com.liuboyu.kafka;

import com.kiisoo.aegis.bd.common.hdfs.RDRawDataRecord;
import com.kiisoo.aegis.common.rawdata.protocol.alinket.conf.DeviceConfig;
import com.kiisoo.aegis.common.rawdata.protocol.alinket.conf.DeviceInfoConfigurator;
import com.kiisoo.aegis.common.rawdata.utils.ByteUtils;

import java.util.Optional;

/**
 * 转换工具类,用于将本地对象转换成设备标准TLV格式对象
 * Created by Tony on 5/3/16.
 */
public class ConvertUtil {

    /**
     * 将数据转化成TLV的二进制数据流
     *
     * @param metadata
     * @return
     */
    public static Optional<byte[]> convert2bytes(Metadata metadata) {
        RDRawDataRecord record = new RDRawDataRecord();
        long stamp = System.currentTimeMillis();
        record.setDmac(metadata.getMac());
        record.setSmac(metadata.getsMac());
        record.setFstamp(stamp);
        record.setLstamp(stamp);
        record.setMinstamp(metadata.getMinTimestamp());
        record.setMaxstamp(metadata.getMaxTimestamp());
        record.setHrssi(Integer.valueOf(metadata.getMaxRssi()));
        record.setLrssi(Integer.valueOf(metadata.getMinRssi()));
        return convert2bytes4Inshop(record);
    }

    /**
     * 转化操作
     *
     * @param record
     * @return
     */
    public static Optional<byte[]> convert2bytes(RDRawDataRecord record) {
        DeviceInfoConfigurator dc = new DeviceInfoConfigurator();
        DeviceConfig d = new DeviceConfig();
        d.setType(ByteUtils.binStr2byte("0100 0000 0000 0010"));
        int len = 16 + 16 * 1;
        d.setLength(len);
        byte[] bytes = dc.buildPDUBytes(d);

        int start = 16;

        System.arraycopy(ByteUtils.toByte(System.currentTimeMillis() / 1000), 4, bytes, start, 4);
        start += 4;

        System.arraycopy(ByteUtils.hexStr2Byte(record.getDmac()), 0, bytes, start, 6);
        start += 6;

        System.arraycopy(ByteUtils.toByte(System.currentTimeMillis() / 1000), 4, bytes, start, 4);
        start += 4;

        System.arraycopy(ByteUtils.toByte(1), 2, bytes, start, 2);
        start += 2;

        System.arraycopy(ByteUtils.hexStr2Byte(record.getSmac()), 0, bytes, start, 6);
        start += 6;

        bytes[start] = (byte) record.getHrssi();
        start += 1;

        System.arraycopy(ByteUtils.toByte(record.getMaxstamp() / 1000), 4, bytes, start, 4);
        start += 4;

        bytes[start] = (byte) record.getLrssi();
        start += 1;

        System.arraycopy(ByteUtils.toByte(record.getMinstamp() / 1000), 4, bytes, start, 4);
        start += 4;

        return Optional.of(bytes);
    }

    public static Optional<byte[]> convert2bytes4Inshop(RDRawDataRecord record) {
        byte[] bytes = new byte[17];
        int start = 0;

        System.arraycopy(ByteUtils.hexStr2Byte(record.getDmac()), 0, bytes, start, 6);
        start += 6;

        System.arraycopy(ByteUtils.hexStr2Byte(record.getSmac()), 0, bytes, start, 6);
        start += 6;

        bytes[start] = (byte) record.getHrssi();
        start += 1;

        System.arraycopy(ByteUtils.toByte(record.getMaxstamp()), 4, bytes, start, 4);
        start += 4;

        return Optional.of(bytes);
    }

}
