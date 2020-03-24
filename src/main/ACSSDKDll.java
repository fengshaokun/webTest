import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;

import java.io.ByteArrayInputStream;

public interface ACSSDKDll extends StdCallLibrary {

	/*ACSSDKDll.CLibrary INSTANCE = (ACSSDKDll.CLibrary)
			Native.loadLibrary("D:\\lib\\bin\\ACSSDK.dll", ACSSDKDll.CLibrary.class);
*/
	ACSSDKDll instance = (ACSSDKDll) Native.loadLibrary("D:\\dll\\ACSSDK.dll" , ACSSDKDll.class) ;
	/***
	 * 收到报警信息回调函数定义。
	 * handle： ACS_Connect的返回值；
	 * alarmInfo： 收到的报警信息。
	 * context： 注册回调时传入的用户自定义信息。
	 * ***/
	static interface AlarmCallBack extends StdCallCallback {
		void ALARM_CALLBACK(NativeLong handle, Pointer alarmInfo, Pointer context);
	}

	/***
	 * 收到与门禁管理系统连接端口信息回调函数定义。
	 * handle： ACS_Connect的返回值；
	 * context： 注册回调时传入的用户自定义信息。
	 ***/
	static interface DisconnectCallBack extends StdCallCallback {
		void DISCONNECT_CALLBACK(NativeLong handle, Pointer context);
	}

	//收到刷卡信息回调函数定义。
	//
	//handle： ACS_Connect的返回值；
	//verifyInfo： 收到的刷卡信息。
	//context： 注册回调时传入的用户自定义信息。
	static interface VerifynotifyCallBack extends StdCallCallback {
		void VERIFYNOTIFY_CALLBACK(NativeLong handle, Pointer alarmInfo, Pointer context);
	}

	//收到开门授权请求回调函数定义。
	//
	//handle：ACS_Connect的返回值；
	//authorizingInfo： 请求授权开门的信息。
	//context：注册回调时传入的用户自定义信息。
	static interface AuthorizingCallBack extends StdCallCallback {
		void AUTHORIZING_CALLBACK(NativeLong handle, Pointer alarmInfo, Pointer context);
	}

	//设备状态回调函数定义。
	//
	//handle：ACS_Connect的返回值；
	//id： 设备ID。
	//dwStatus : 设备状态，0表示断开，1表示连接上。
	static interface ControllerStatusCallBack extends StdCallCallback {
		void CONTROLLERSTATUS_CALLBACK(NativeLong handle, String id, int dwStatus);
	}

	//收到已授权开门回调函数定义。
	//任意一台客户端或在门禁服务软件上对开门请求进行授权后，门禁系统将通知其他所有的客户端。
	//
	//handle： ACS_Connect的返回值；
	//sessionId：授权请求的唯一标识id。
	//handlerPlace：授权人所处位置（如果是客户端操作返回客户端ip；如果是在运行门禁系统的机器上进行操作，那么返回”host”）
	//handlerName：授权人名称。
	//handlerId：授权人id。
	//isGranted：true为允许，false为拒绝。
	//time：授权时间。
	//context：注册回调时传入的用户自定义信息。
	static interface AuthorizedCallBack extends StdCallCallback {
		void AUTHORIZED_CALLBACK(NativeLong handle, NativeLong sessionId,
                                 String handlerName, String handlerId, String handlerPlace,
                                 boolean isGranted, Pointer time, Pointer context);
	}

	//收到门状态回调函数定义
	//
	//handle：ACS_Connect的返回值；
	//id：发生状态变化的门id。
	//type：发生变化的状态类型。
	//status：变化后的状态值。
	static interface GateStatusCallBack extends StdCallCallback {
		void GATESTATUS_CALLBACK(NativeLong handle, String id, Pointer type, int status);
	}

	//收到互锁门双开报警回调函数定义。
	//
	//handle： ACS_Connect的返回值；
	//triggerGate： 引发报警的门。
	//interrelatedGate：与报警的门互锁的门。
	//context： 注册回调时传入的用户自定义信息。
	static interface InterlockAlarmCallBack extends StdCallCallback {
		void INTERLOCKALARM_CALLBACK(NativeLong handle, Pointer triggerGate, Pointer interrelatedGate, Pointer context);
	}

	//收到已授权开门回调函数定义。
	//任意一台客户端或在门禁服务软件上对开门请求进行授权后，门禁系统将通知其他所有的客户端。
	//
	//handle： ACS_Connect的返回值；
	//sessionId：授权请求的唯一标识id。
	//handlerPlace：授权人所处位置（如果是客户端操作返回客户端ip；如果是在运行门禁系统的机器上进行操作，那么返回”host”）
	//handlerName：授权人名称。
	//handlerId：授权人id。
	//isGranted：true为允许，false为拒绝。
	//time：授权时间。
	// timeout:是否超时处理，true为超时处理，false为正常处理
	//context：注册回调时传入的用户自定义信息。
	static interface AuthorizedCallBackEX extends StdCallCallback {
		void AUTHORIZED_CALLBACKEX(NativeLong handle, NativeLong sessionId,
                                   String handlerName, String handlerId, String handlerPlace,
                                   boolean isGranted, Pointer time, boolean timeout, Pointer context);
	}

	//初始化SDK库。
	//
	//返回：True表示成功，False表示失败。
	boolean ACS_Init();

    //是否SDK库所有资源。
	void ACS_Release();

	//获取当前是否与门禁管理软件建立连接。
	//
	//handle： ACS_Connect的返回值；
	//返回：True表示已连接，False表示未连接。
	boolean ACS_IsConnect(NativeLong handle);

	//连接门禁管理软件。
	//
	//ip： 准备连接门禁管理软件的IP地址。
	//port：门禁管理软件的端口。
	//loginName：登录名（门禁系统的用户名）。
	//password：登录密码。
	//返回：成功返回连接句柄，用于后续操作，-1表示失败。
	NativeLong ACS_Connect(String ip, int port, String loginName, String password);

	//断开与门禁管理软件的链接。
	//
	//handle： ACS_Connect的返回值；
	void ACS_Disconnect(NativeLong handle);

	//获取门禁管理软件基本信息；
	//
	//handle： ACS_Connect的返回值；
	//baseInfo：返回门禁管理软件基本信息；
	//返回：True表示成功，False表示失败。
	boolean ACS_GetInfo(NativeLong handle, Pointer baseInfo);

	//获取服务器的控制器信息；
	//handle： ACS_Connect的返回值；
	//controllerCount：需要返回的控制器数量；
	//controllerInfo：返回控制器信息数据；
	//返回：True表示成功，False表示失败。
	boolean ACS_GetControllerInfo(NativeLong handle, int controllerCount, Pointer controllerInfo);

	//获取服务器的门信息；
	//handle： ACS_Connect的返回值；
	//gateCount：需要返回的门数量；
	//gateInfo：返回的门数据；
	//返回：True表示成功，False表示失败。
	boolean ACS_GetGateInfo(NativeLong handle, int gateCount, Pointer gateInfo);

	//获取门禁管理软件拥有的报警源数量；
	//
	//handle： ACS_Connect的返回值；
	//返回：根据当前配置的控制器等信息计算出拥有的报警输入源数量。
	int	ACS_GetAlarmSourceCount(NativeLong handle);

	//获取门禁管理软件的报警源；
	//
	//handle： ACS_Connect的返回值；
	//sourceCount：需要返回的报警源数量。
	//alarmSource：返回的报警源数据。
	//返回：True表示成功，False表示失败。
	boolean	ACS_GetAlarmSource(NativeLong handle, int sourceCount, Pointer alarmSource);

	//注册门禁管理软件连接断开回调函数；
	//
	//callback：连接断开时调用的方法，传null表示取消注册；
	//context：用户自定义信息；
	//返回：True表示成功，False表示失败。
	boolean	ACS_RegisterDisconnectNotify(DisconnectCallBack callback, Pointer context);

	//注册门禁管理软件报警信息回调函数；
	//
	//callback：收到报警信息时调用的方法，传null表示取消注册；
	//context：用户自定义信息；
	//返回：True表示成功，False表示失败。
	boolean	ACS_RegisterAlarmNotify(AlarmCallBack callback, Pointer context);

	//注册门禁管理软件用户刷卡回调函数；
	//
	//callback：收到刷卡信息时调用的方法，传null表示取消注册；
	//context：用户自定义信息；
	//返回：True表示成功，False表示失败。
	boolean	ACS_RegisterVerifyNotify(VerifynotifyCallBack callback, Pointer context);

	//注册收到开门授权请求回调函数。
	//
	//callback：收到开门授权申请时调用的方法，传null表示取消注册；
	//context：用户自定义信息；
	//返回：True表示成功，False表示失败。
	boolean	ACS_RegisterAuthorizing(AuthorizingCallBack callback, Pointer context);

	//注册收到开门已授权回调函数。
	//
	//callback：收到开门已授权时调用的方法，传null表示取消注册；
	//context：用户自定义信息；
	//返回：True表示成功，False表示失败。
	boolean	ACS_RegisterAuthorized(AuthorizedCallBack callback, Pointer context);

	//注册收到门各种状态发生变化的回调函数
	//
	//callback：收到发生变化的门状态调用的方法，传null表示取消注册；
	//context：用户自定义信息；
	//返回：True表示成功，False表示失败。
	boolean ACS_RegisterGateStatusNotify(GateStatusCallBack callback, Pointer context);

	//注册收到控制器状态发生变化的回调函数
	//
	//callback：收到发生变化的控制器状态调用的方法，传null表示取消注册；
	//context：用户自定义信息；
	//返回：True表示成功，False表示失败。
	boolean ACS_RegisterControllerStatusNotify(ControllerStatusCallBack callback, Pointer context);

	//查询历史刷卡记录；
	//
	//handle： ACS_Connect的返回值；
	//controllerId： 要查询的控制器ID，null表示查询所有控制记录；
	//readerId： 查询指定读卡器ID，null表示查询指定控制器下所有读卡器记录。
	// controllerId == null 时，该参数无效；
	//startTime： 历史记录的开始时间；
	//endTime： 历史记录的结束时间；
	//count： 返回记录总数；
	//返回：-1表示失败，其它值用于ACS_FindNextRecord和ACS_FindClose。
	NativeLong ACS_FindRecord(NativeLong handle, String controllerId, String readerId, Pointer startTime, Pointer endTime, int count);

	//查询下一条历史刷卡记录；
	//
	//findHandle： ACS_FindRecord的返回值；
	//recordLog： 返回查询记录结果；
	//返回：True表示成功，False表示失败。
	boolean ACS_FindNextRecord(NativeLong findHandle, Pointer recordLog);

	//结束历史记录查询；
	//
	//findHandle：ACS_FindRecord的返回值；
	//返回：True表示成功，False表示失败。
	boolean ACS_FindClose(NativeLong findHandle);

	//获取给定持卡人照片的方法。
	//
	//handle：ACS_Connect的返回值；
	//authorizingInfo：请求授权开门的信息。
	//buffer：返回照片的数据流。
	//size：返回照片数据流的大小。
	//返回：True表示获取成功，False表示失败。
	boolean ACS_GetCardholderPhoto(NativeLong handle, String hoderId, ByteArrayInputStream buffer, int size);

	//向客户端发送开门授权；
	//
	//handle：ACS_Connect的返回值；
	//sessionId：进行授权的SessionID，AUTHORIZING_CALLBACK回调函数中返回的值。
	//isGrant：是否授权，true为授权，false为拒绝。
	//handlerName：授权人名称。
	//handlerId：授权人id。
	//返回：True表示授权命令发送成功，False表示失败。
	boolean ACS_Authorize(NativeLong handle, String sessionId, boolean isGrant, String handlerName, String handlerId);

	//客户端发送开门指令；
	//
	//handle：ACS_Connect的返回值；
	//gateId：门id。
	//autoCloseEnable：发出开门指令后是否启用自动关闭，true为启用，false为禁用。
	//delaySeconds：自动关闭的延迟时间。
	//返回：True表示授权命令发送成功，False表示失败。
	boolean ACS_OpenDoor(NativeLong handle, String gateId, boolean autoCloseEnable, int delaySeconds);

	//客户端发送关门指令；
	//
	//handle：ACS_Connect的返回值；
	//gateId：门id。
	//返回：True表示授权命令发送成功，False表示失败。
	boolean ACS_CloseDoor(NativeLong handle, String gateId);

	//查询持卡人；
	//
	//handle： ACS_Connect的返回值；
	//count： 返回持卡人总数；
	//返回：-1表示失败，其它值用于ACS_FindNextCardHolder和ACS_FindCardHolderClose。
	NativeLong ACS_FindCardHolder(NativeLong handle, int count);

	//查询下一名持卡人；
	//
	//findHandle：ACS_FindCardHolder的返回值；
	//cardHolder： 返回查询的持卡人信息；
	//返回：True表示成功，False表示失败。
	boolean ACS_FindNextCardHolder(NativeLong findHandle, Pointer cardHolder);

	//结束持卡人查询；
	//
	//findHandle： ACS_FindCardHolder的返回值；
	//返回：True表示成功，False表示失败。
	boolean ACS_FindCardHolderClose(NativeLong findHandle);

	//注册收到互锁门双开报警的回调函数
	//
	//callback：收到报警调用的方法，传null表示取消注册；
	//context：用户自定义信息；
	//返回：True表示成功，False表示失败。
	boolean ACS_RegisterInterAlarmNotify(InterlockAlarmCallBack callback, Pointer context);

	//向客户端发送开门授权；
	//
	//handle：ACS_Connect的返回值；
	//sessionId：进行授权的SessionID，AUTHORIZING_CALLBACK回调函数中返回的值。
	//isGrant：是否授权，true为授权，false为拒绝。
	//handlerName：授权人名称。
	//handlerId：授权人id。
	// handlerPlace:授权来源（授权客户端所属IP）
	//返回：True表示授权命令发送成功，False表示失败。
	boolean ACS_AuthorizeEx(NativeLong handle, String sessionId, boolean isGrant, String handlerName, String handlerId, String handlerPlace);

	//注册收到开门已授权回调函数。
	//
	//callback：收到开门已授权时调用的方法，传null表示取消注册；
	//context：用户自定义信息；
	//返回：True表示成功，False表示失败。
	boolean ACS_RegisterAuthorizedEX(AuthorizedCallBackEX callback, Pointer context);

	//查询开门记录；
	//指刷卡开门的几种场景：单卡开门、组合卡开门、刷卡+远程授权（或授权读卡器授权）开门
	//handle： ACS_Connect的返回值；
	//controllerId： 要查询的控制器ID，null表示查询所有控制器的记录；
	//gateId： 查询指定门ID，null表示查询指定控制器下所有门的记录。
	//        controllerId == null 时，该参数无效；
	//startTime： 历史记录的开始时间；
	//endTime： 历史记录的结束时间；
	//count： 返回记录总数；
	//返回：-1表示失败，其它值用于ACS_FindNextAccessLog和ACS_FindAcessLogClose。
	NativeLong ACS_FindAccessLog(NativeLong handle, String controllerId, String gateId, Pointer startTime, Pointer endTime, int count);

	//查询下一条历史开门记录；
	//
	//findHandle： ACS_FindAccessLog的返回值；
	//recordLog： 返回查询记录结果；
	//返回：True表示成功，False表示失败。
	boolean ACS_FindNextAccessLog(NativeLong findHandle, Pointer recordLog);

	//结束历史记录查询；
	//
	//findHandle：ACS_FindAccessLog的返回值；
	//返回：True表示成功，False表示失败。
	boolean ACS_FindAccessLogClose(NativeLong findHandle);

	//查询报警日志；
	//
	//handle： ACS_Connect的返回值；
	//controllerId： 要查询的控制器ID，null表示查询所有控制器的记录；
	//startTime： 历史记录的开始时间；
	//endTime： 历史记录的结束时间；
	//count： 返回记录总数；
	//返回：-1表示失败，其它值用于ACS_FindNextAlarmLog和ACS_FindAlarmLogClose。
	NativeLong ACS_FindAlarmLog(NativeLong handle, String controllerId, Pointer startTime, Pointer endTime, int count);

	//查询下一条报警日志；
	//
	//findHandle： ACS_FindAlarmLog的返回值；
	//recordLog： 返回查询记录结果；
	//返回：True表示成功，False表示失败。
	boolean ACS_FindNextAlarmLog(NativeLong findHandle, Pointer alarmLog);

	//结束查询；
	//
	//findHandle：ACS_FindAlarmLog的返回值；
	//返回：True表示成功，False表示失败。
	boolean ACS_FindAlarmLogClose(NativeLong findHandle);
}
