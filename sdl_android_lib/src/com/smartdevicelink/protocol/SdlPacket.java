package com.smartdevicelink.protocol;

import com.smartdevicelink.protocol.enums.FrameType;

import android.os.Parcel;
import android.os.Parcelable;

public class SdlPacket implements Parcelable{

	
	public static final int HEADER_SIZE 						= 12;
	public static final int HEADER_SIZE_V1 						= 8;//Backwards

	private static final int VERSION_MASK 						= 0xF0; //4 highest bits
	private static final int COMPRESSION_MASK 					= 0x08; //4th lowest bit
	private static final int FRAME_TYPE_MASK 					= 0x07; //3 lowest bits
	
	public static final int FRAME_TYPE_CONTROL 					= 0x00;
	public static final int FRAME_TYPE_SINGLE 					= 0x01;
	public static final int FRAME_TYPE_FIRST 					= 0x02;
	public static final int FRAME_TYPE_CONSECUTIVE				= 0x03;
	
	/*
	 * Service Type
	 */
	public static final int SERVICE_TYPE_CONTROL 				= 0x00;
	//RESERVED 0x01 - 0x06
	public static final int SERVICE_TYPE_RPC	 				= 0x07;
	//RESERVED 0x08 - 0x09
	public static final int SERVICE_TYPE_PCM 					= 0x0A;
	public static final int SERVICE_TYPE_VIDEO 				= 0x0B;
	//RESERVED 0x0C - 0x0E
	public static final int SERVICE_TYPE_BULK_DATA				= 0x0F;
	//RESERVED 0x10 - 0xFF

	
	/*
	 * Frame Info
	 */
	//Control Frame Info
	public static final int FRAME_INFO_HEART_BEAT 				= 0x00;
	public static final int FRAME_INFO_START_SERVICE 			= 0x01;
	public static final int FRAME_INFO_START_SERVICE_ACK		= 0x02;
	public static final int FRAME_INFO_START_SERVICE_NAK		= 0x03;
	public static final int FRAME_INFO_END_SERVICE 			= 0x04;
	public static final int FRAME_INFO_END_SERVICE_ACK			= 0x05;
	public static final int FRAME_INFO_END_SERVICE_NAK			= 0x06;
	//0x07-0xFD are reserved	
	public static final int FRAME_INFO_SERVICE_DATA_ACK		= 0xFE;
	public static final int FRAME_INFO_HEART_BEAT_ACK			= 0xFF;
	//Most others
	public static final int FRAME_INFO_RESERVED 				= 0x00;

	
	int version;
	boolean compression;
	int frameType;
	int serviceType;
	int frameInfo;
	int sessionId;
	int dataSize;
	int messageId;
	
	byte[] payload = null;

	public SdlPacket(int version, boolean compression, int frameType,
			int serviceType, int frameInfo, int sessionId,
			int dataSize, int messageId, byte[] payload) {
		this.version = version;
		this.compression = compression;
		this.frameType = frameType;
		this.serviceType = serviceType;
		this.frameInfo = frameInfo;
		this.sessionId = sessionId;
		this.dataSize = dataSize;
		this.messageId = messageId;
		if(payload!=null){
			this.payload = new byte[payload.length];
			System.arraycopy(payload, 0, this.payload, 0, payload.length);
		}
	}

	/**
	 * This constructor is available as a protected method. A few defaults have been set, however a few things <b>MUST</b> be set before use. The rest will "work"
	 * however, it won't be valid data.
	 * 
	 * <p>Frame Type
	 * <p>Service Type
	 * <p>Frame Info
	 * <p>
	 */
	protected SdlPacket(){
		//Package only empty constructor
		//TODO add defaults
		this.version = 1;
		this.compression = false;
		this.frameType = -1;	//This NEEDS to be set
		this.serviceType = -1;
		this.frameInfo = -1;
		this.sessionId = 0;
		this.dataSize = 0;
		this.messageId = 0;
		
	}
	
	/**
	 * Creates a new packet based on previous packet definitions
	 * @param packet
	 */
	protected SdlPacket(SdlPacket packet){
		this.version = packet.version;
		this.compression = packet.compression;
		this.frameType = packet.frameType;	
		this.serviceType = packet.serviceType;
		this.frameInfo = packet.frameInfo;
		this.sessionId = packet.sessionId;
		this.dataSize = 0;
		this.messageId = 0;
	}

	public int getVersion() {
		return version;
	}

	public boolean isCompression() {
		return compression;
	}

	public FrameType getFrameType() {
		switch(frameType){
		case FRAME_TYPE_CONTROL:
			return FrameType.Control;
		case FRAME_TYPE_FIRST:
			return FrameType.First;
		case FRAME_TYPE_CONSECUTIVE:
			return FrameType.Consecutive;
		case FRAME_TYPE_SINGLE:
		default:
			return FrameType.Single;
		}
	}

	public int getServiceType() {
		return serviceType;
	}

	public int getFrameInfo() {
		return frameInfo;
	}

	public int getSessionId() {
		return sessionId;
	}

	public long getDataSize() {
		return dataSize;
	}

	public byte[] getPayload() {
		return payload;
	}
	
	public byte[] constructPacket(){
		return constructPacket(version, compression, frameType,
				serviceType, frameInfo, sessionId,
				dataSize, messageId, payload);
	}
	
	/**
	 * This method takes in the various components to the SDL packet structure and creates a new byte array that can be sent via the transport
	 * @param version
	 * @param compression
	 * @param frameType
	 * @param serviceType
	 * @param controlFrameInfo
	 * @param sessionId
	 * @param dataSize
	 * @param messageId
	 * @param payload
	 * @return
	 */
	public static byte[] constructPacket(int version, boolean compression, int frameType,
			int serviceType, int controlFrameInfo, int sessionId,
			int dataSize, int messageId, byte[] payload){
		StringBuilder builder;
		switch(version){
			case 1:
				builder = new StringBuilder(HEADER_SIZE_V1 + dataSize);
				break;
			default:
				builder = new StringBuilder(HEADER_SIZE + dataSize);
				break;
		}
		
		builder.append((byte)((version<<4) + getCompressionBit(compression) + frameType));
		builder.append((byte)serviceType);
		builder.append((byte)controlFrameInfo);
		builder.append((byte)sessionId);
		
		builder.append((byte)((dataSize&0xFF000000)>>24));
		builder.append((byte)((dataSize&0x00FF0000)>>16));
		builder.append((byte)((dataSize&0x0000FF00)>>8));
		builder.append((byte)((dataSize&0x000000FF)));
		
		if(version>1){	//Version 1 did not include this part of the header
			builder.append((byte)((messageId&0xFF000000)>>24));
			builder.append((byte)((messageId&0x00FF0000)>>16));
			builder.append((byte)((messageId&0x0000FF00)>>8));
			builder.append((byte)((messageId&0x000000FF)));
		}
		
		builder.append(payload);
		
		return builder.toString().getBytes();
	}
	
	
	public static int getCompressionBit(boolean compression){
		if(compression){
			return COMPRESSION_MASK;
		}else{
			return 0;
		}
	}
	
@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("***** Sdl Packet ******");
		builder.append(	"\nVersion:  " +version);
		builder.append(	"\nCompression:  " +compression);
		builder.append(	"\nFrameType:  " +frameType);
		builder.append(	"\nServiceType:  " +serviceType);
		builder.append(	"\nFrameInfo:  " +frameInfo);
		builder.append(	"\nSessionId:  " +sessionId);
		builder.append(	"\nDataSize:  " +dataSize);
		builder.append(	"\nMessageId:  " +messageId);
		builder.append("\n***** Sdl Packet  End******");


		return builder.toString();
	}
	
	
	
	/* ***************************************************************************************************************************************************
	 * ***********************************************************  Parceable Overrides  *****************************************************************
	 *****************************************************************************************************************************************************/


	//I think this is FIFO...right?
	public SdlPacket(Parcel p) {
		this.version = p.readInt();
		this.compression = (p.readInt() == 0) ? false : true;
		this.frameType = p.readInt();
		this.serviceType = p.readInt();
		this.frameInfo = p.readInt();
		this.sessionId = p.readInt();
		this.dataSize = p.readInt();
		this.messageId = p.readInt();
		if(p.readInt() == 1){ //We should have a payload attached
			payload = new byte[dataSize];
			p.readByteArray(payload);
		}
	}
	
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		dest.writeInt(version);
		dest.writeInt(compression? 1 : 0);
		dest.writeInt(frameType);
		dest.writeInt(serviceType);
		dest.writeInt(frameInfo);
		dest.writeInt(sessionId);
		dest.writeInt(dataSize);
		dest.writeInt(messageId);
		dest.writeInt(payload!=null? 1 : 0);
		dest.writeByteArray(payload);

	}
	
	public static final Parcelable.Creator<SdlPacket> CREATOR = new Parcelable.Creator<SdlPacket>() {
        public SdlPacket createFromParcel(Parcel in) {
     	   return new SdlPacket(in); 
        }

		@Override
		public SdlPacket[] newArray(int size) {
			return new SdlPacket[size];
		}

    };
	
	
}