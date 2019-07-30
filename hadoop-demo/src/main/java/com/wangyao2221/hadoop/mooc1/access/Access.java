package com.wangyao2221.hadoop.mooc1.access;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Access implements WritableComparable<Access>{
    private String phone;
    private long up;
    private long down;
    private long sum;

    public Access() {
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(phone);
        dataOutput.writeLong(up);
        dataOutput.writeLong(down);
        dataOutput.writeLong(sum);
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.phone = dataInput.readUTF();
        this.up = dataInput.readLong();
        this.down = dataInput.readLong();
        this.sum = dataInput.readLong();
    }

    @Override
    public String toString() {
        return String.format("%s,%d,%d,%d", phone, up, down, sum);
    }

    public Access(String phone, long up, long down, long sum) {
        this.phone = phone;
        this.up = up;
        this.down = down;
        this.sum = sum;
    }

    public Access(String phone, long up, long down) {
        this.phone = phone;
        this.up = up;
        this.down = down;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getUp() {
        return up;
    }

    public void setUp(long up) {
        this.up = up;
    }

    public long getDown() {
        return down;
    }

    public void setDown(long down) {
        this.down = down;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public int compareTo(Access access) {
        return (this.getSum() > access.getSum()) ? 1 : -1;
    }

    public static class Comparator extends WritableComparator {
        public Comparator() {
            super(IntWritable.class);
        }

        @Override
        public int compare(WritableComparable a, WritableComparable b) {
            return a.compareTo(b);
        }
    }
}
