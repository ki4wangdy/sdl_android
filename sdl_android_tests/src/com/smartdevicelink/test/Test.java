package com.smartdevicelink.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.smartdevicelink.proxy.TTSChunkFactory;
import com.smartdevicelink.proxy.rpc.Choice;
import com.smartdevicelink.proxy.rpc.DeviceInfo;
import com.smartdevicelink.proxy.rpc.Image;
import com.smartdevicelink.proxy.rpc.KeyboardProperties;
import com.smartdevicelink.proxy.rpc.MenuParams;
import com.smartdevicelink.proxy.rpc.SdlMsgVersion;
import com.smartdevicelink.proxy.rpc.SoftButton;
import com.smartdevicelink.proxy.rpc.StartTime;
import com.smartdevicelink.proxy.rpc.TTSChunk;
import com.smartdevicelink.proxy.rpc.Turn;
import com.smartdevicelink.proxy.rpc.VrHelpItem;
import com.smartdevicelink.proxy.rpc.enums.AppHMIType;
import com.smartdevicelink.proxy.rpc.enums.AudioType;
import com.smartdevicelink.proxy.rpc.enums.BitsPerSample;
import com.smartdevicelink.proxy.rpc.enums.ButtonName;
import com.smartdevicelink.proxy.rpc.enums.FileType;
import com.smartdevicelink.proxy.rpc.enums.GlobalProperty;
import com.smartdevicelink.proxy.rpc.enums.ImageType;
import com.smartdevicelink.proxy.rpc.enums.InteractionMode;
import com.smartdevicelink.proxy.rpc.enums.KeyboardLayout;
import com.smartdevicelink.proxy.rpc.enums.KeypressMode;
import com.smartdevicelink.proxy.rpc.enums.Language;
import com.smartdevicelink.proxy.rpc.enums.LayoutMode;
import com.smartdevicelink.proxy.rpc.enums.RequestType;
import com.smartdevicelink.proxy.rpc.enums.SamplingRate;
import com.smartdevicelink.proxy.rpc.enums.SoftButtonType;
import com.smartdevicelink.proxy.rpc.enums.SpeechCapabilities;
import com.smartdevicelink.proxy.rpc.enums.SystemAction;
import com.smartdevicelink.proxy.rpc.enums.TextAlignment;
import com.smartdevicelink.proxy.rpc.enums.UpdateMode;

public class Test {
	
	// Test Failure Messages
	public static final String NULL      = "Value should be null.";
	public static final String MATCH     = "Values should match.";
	public static final String ARRAY     = "Array values should match.";
	public static final String TRUE      = "Value should be true.";
	public static final String FALSE     = "Value should be false.";
	public static final String NOT_NULL  = "Value should not be null.";
	public static final String JSON_FAIL = "Json testing failed.";

	// Rpc Test Values
	public static final int                GENERAL_INT                = 100;
	public static final float              GENERAL_FLOAT              = 100f;
	public static final Image              GENERAL_IMAGE              = new Image();	
	public static final String             GENERAL_STRING             = "test";
	public static final Double             GENERAL_DOUBLE             = 10.01;
	public static final boolean            GENERAL_BOOLEAN            = true;
	public static final FileType           GENERAL_FILETYPE           = FileType.BINARY;
	public static final Language           GENERAL_LANGUAGE           = Language.EN_US;
	public static final AudioType          GENERAL_AUDIOTYPE          = AudioType.PCM;
	public static final StartTime          GENERAL_STARTTIME          = new StartTime();
	public static final DeviceInfo		   GENERAL_DEVICEINFO		  = new DeviceInfo();
	public static final LayoutMode         GENERAL_LAYOUTMODE         = LayoutMode.LIST_ONLY;
	public static final MenuParams         GENERAL_MENUPARAMS         = new MenuParams();
	public static final ButtonName         GENERAL_BUTTONNAME         = ButtonName.OK;
	public static final UpdateMode         GENERAL_UPDATEMODE         = UpdateMode.RESUME;
	public static final RequestType        GENERAL_REQUESTTYPE        = RequestType.AUTH_REQUEST;
	public static final SamplingRate       GENERAL_SAMPLINGRATE       = SamplingRate._8KHZ;
	public static final BitsPerSample      GENERAL_BITSPERSAMPLE	  = BitsPerSample._8_BIT;
	public static final TextAlignment      GENERAL_TEXTALIGNMENT      = TextAlignment.CENTERED;
	public static final SdlMsgVersion      GENERAL_SDLMSGVERSION      = new SdlMsgVersion();
	public static final InteractionMode    GENERAL_INTERACTIONMODE    = InteractionMode.BOTH;
	public static final KeyboardProperties GENERAL_KEYBOARDPROPERTIES = new KeyboardProperties();
	
	public static final List<Turn>           GENERAL_TURN_LIST           = new ArrayList<Turn>();
	public static final List<Choice>         GENERAL_CHOICE_LIST         = new ArrayList<Choice>();
	public static final List<String>         GENERAL_STRING_LIST         = Arrays.asList(new String[] { "a", "b"});
	public static final List<Integer>        GENERAL_INTEGER_LIST        = Arrays.asList(new Integer[]{ -1, -2});
	public static final List<TTSChunk>       GENERAL_TTSCHUNK_LIST       = new ArrayList<TTSChunk>();
	public static final List<AppHMIType>     GENERAL_APPHMITYPE_LIST     = new ArrayList<AppHMIType>();
	public static final List<VrHelpItem>     GENERAL_VRHELPITEM_LIST     = new ArrayList<VrHelpItem>();
	public static final List<SoftButton>     GENERAL_SOFTBUTTON_LIST     = new ArrayList<SoftButton>();
	public static final List<GlobalProperty> GENERAL_GLOBALPROPERTY_LIST = new ArrayList<GlobalProperty>();
	
	public static final JSONArray  JSON_TURNS              = new JSONArray();
	public static final JSONArray  JSON_CHOICES            = new JSONArray();
	public static final JSONArray  JSON_TTSCHUNKS          = new JSONArray();
	public static final JSONArray  JSON_VRHELPITEMS        = new JSONArray();
	public static final JSONArray  JSON_SOFTBUTTONS        = new JSONArray();	
	public static final JSONObject JSON_IMAGE              = new JSONObject();
	public static final JSONObject JSON_STARTTIME          = new JSONObject();
	public static final JSONObject JSON_MENUPARAMS         = new JSONObject();
	public static final JSONObject JSON_DEVICEINFO         = new JSONObject();
	public static final JSONObject JSON_SDLMSGVERSION      = new JSONObject();
	public static final JSONObject JSON_KEYBOARDPROPERTIES = new JSONObject();
	
	static {
		// Image Setup
		GENERAL_IMAGE.setValue(GENERAL_STRING);
		GENERAL_IMAGE.setImageType(ImageType.STATIC);
		
		// SoftButton List Setup
		SoftButton softButton = new SoftButton();
		softButton.setIsHighlighted(GENERAL_BOOLEAN);
		softButton.setSoftButtonID(GENERAL_INT);
		softButton.setSystemAction(SystemAction.STEAL_FOCUS);
		softButton.setText(GENERAL_STRING);
		softButton.setType(SoftButtonType.SBT_TEXT);
		softButton.setImage(GENERAL_IMAGE);
		GENERAL_SOFTBUTTON_LIST.add(softButton);
		
		// Turn List Setup
		Turn turn = new Turn();
		turn.setNavigationText(GENERAL_STRING);
		turn.setTurnIcon(GENERAL_IMAGE);
		GENERAL_TURN_LIST.add(turn);
		
		// MenuParams Setup
		GENERAL_MENUPARAMS.setMenuName(GENERAL_STRING);
		GENERAL_MENUPARAMS.setParentID(GENERAL_INT);
		GENERAL_MENUPARAMS.setPosition(GENERAL_INT);
		
		// String List Setup
		GENERAL_STRING_LIST.add("Command 1");
		GENERAL_STRING_LIST.add("Command 2");
		
		// VrHelpItem List Setup
		VrHelpItem vrItem1 = new VrHelpItem();
    	vrItem1.setText(GENERAL_STRING);
    	vrItem1.setImage(GENERAL_IMAGE);
    	vrItem1.setPosition(0);	    	
    	GENERAL_VRHELPITEM_LIST.add(vrItem1);
    	
    	// TTSChunk List Setup
    	GENERAL_TTSCHUNK_LIST.add(TTSChunkFactory.createChunk(SpeechCapabilities.TEXT, "Welcome to the jungle"));
    	GENERAL_TTSCHUNK_LIST.add(TTSChunkFactory.createChunk(SpeechCapabilities.TEXT, "Say a command"));
		
    	// KeyboardProperties Setup
    	GENERAL_KEYBOARDPROPERTIES.setAutoCompleteText(GENERAL_STRING);
    	GENERAL_KEYBOARDPROPERTIES.setKeypressMode(KeypressMode.SINGLE_KEYPRESS);
    	GENERAL_KEYBOARDPROPERTIES.setKeyboardLayout(KeyboardLayout.QWERTY);
    	GENERAL_KEYBOARDPROPERTIES.setLanguage(Language.EN_US);
    	GENERAL_KEYBOARDPROPERTIES.setLimitedCharacterList(GENERAL_STRING_LIST);
    	
    	// StartTime Setup
    	GENERAL_STARTTIME.setHours(0);
		GENERAL_STARTTIME.setMinutes(0);
		GENERAL_STARTTIME.setSeconds(0);
		
		// Choice List Setup
		Choice choice = new Choice();
        choice.setChoiceID(GENERAL_INT);
        choice.setMenuName(GENERAL_STRING);
        choice.setSecondaryText(GENERAL_STRING);
        GENERAL_CHOICE_LIST.add(choice);
        
        // DeviceInfo Setup
        GENERAL_DEVICEINFO.setCarrier(GENERAL_STRING);
        GENERAL_DEVICEINFO.setFirmwareRev(GENERAL_STRING);
        GENERAL_DEVICEINFO.setHardware(GENERAL_STRING);
        GENERAL_DEVICEINFO.setMaxNumberRFCOMMPorts(GENERAL_INT);
        GENERAL_DEVICEINFO.setOs(GENERAL_STRING);
        GENERAL_DEVICEINFO.setOsVersion(GENERAL_STRING);
        
        // SdlMsgVersion Setup
        GENERAL_SDLMSGVERSION.setMajorVersion(GENERAL_INT);
        GENERAL_SDLMSGVERSION.setMinorVersion(GENERAL_INT);
        
        // AppHMIType List Setup
        GENERAL_APPHMITYPE_LIST.add(AppHMIType.BACKGROUND_PROCESS);
        GENERAL_APPHMITYPE_LIST.add(AppHMIType.COMMUNICATION);
        
        // GlobalProperty List Setup
        GENERAL_GLOBALPROPERTY_LIST.add(GlobalProperty.HELPPROMPT);
        GENERAL_GLOBALPROPERTY_LIST.add(GlobalProperty.MENUICON);
		
		try {			
			// Json Image Setup
			JSON_IMAGE.put(Image.KEY_IMAGE_TYPE, ImageType.STATIC);
			JSON_IMAGE.put(Image.KEY_VALUE, GENERAL_STRING);
			
			// Json SoftButton List Setup
			JSONObject jsonSoftButton = new JSONObject();
			jsonSoftButton.put(SoftButton.KEY_IS_HIGHLIGHTED , GENERAL_BOOLEAN);
			jsonSoftButton.put(SoftButton.KEY_SOFT_BUTTON_ID, GENERAL_INT);
			jsonSoftButton.put(SoftButton.KEY_SYSTEM_ACTION, SystemAction.STEAL_FOCUS);
			jsonSoftButton.put(SoftButton.KEY_TEXT, GENERAL_STRING);
			jsonSoftButton.put(SoftButton.KEY_TYPE, SoftButtonType.SBT_TEXT);
			jsonSoftButton.put(SoftButton.KEY_IMAGE, GENERAL_IMAGE.serializeJSON());
			JSON_SOFTBUTTONS.put(jsonSoftButton);
			
			// Json Turn List Setup
			JSONObject jsonTurn = new JSONObject();
			jsonTurn.put(Turn.KEY_NAVIGATION_TEXT, GENERAL_STRING);
			jsonTurn.put(Turn.KEY_TURN_IMAGE, GENERAL_IMAGE.serializeJSON());
			JSON_TURNS.put(jsonTurn);
			
			// Json MenuParams Setup
			JSON_MENUPARAMS.put(MenuParams.KEY_MENU_NAME, GENERAL_STRING);
			JSON_MENUPARAMS.put(MenuParams.KEY_PARENT_ID, GENERAL_INT);
			JSON_MENUPARAMS.put(MenuParams.KEY_POSITION, GENERAL_INT);
			
			// Json VrHelpItem Setup
	    	JSONObject jsonVrHelpItem = new JSONObject();
	    	jsonVrHelpItem.put(VrHelpItem.KEY_TEXT, GENERAL_STRING);
	    	jsonVrHelpItem.put(VrHelpItem.KEY_IMAGE, JSON_IMAGE);
	    	jsonVrHelpItem.put(VrHelpItem.KEY_POSITION, GENERAL_INT);
	    	JSON_VRHELPITEMS.put(jsonVrHelpItem);
	    	
	    	// Json TTSChunk Setup
	    	JSONObject jsonTtsChunk = new JSONObject();
	    	jsonTtsChunk.put(TTSChunk.KEY_TEXT, "Welcome to the jungle");
	    	jsonTtsChunk.put(TTSChunk.KEY_TYPE, SpeechCapabilities.TEXT);
	    	JSON_TTSCHUNKS.put(jsonTtsChunk);
	    	
	    	// Json KeyboardProperties Setup
	    	JSON_KEYBOARDPROPERTIES.put(KeyboardProperties.KEY_AUTO_COMPLETE_TEXT, GENERAL_STRING);
	    	JSON_KEYBOARDPROPERTIES.put(KeyboardProperties.KEY_KEYPRESS_MODE, KeypressMode.SINGLE_KEYPRESS);
	    	JSON_KEYBOARDPROPERTIES.put(KeyboardProperties.KEY_KEYBOARD_LAYOUT, KeyboardLayout.QWERTY);
	    	JSON_KEYBOARDPROPERTIES.put(KeyboardProperties.KEY_LANGUAGE, Language.EN_US);
	    	JSON_KEYBOARDPROPERTIES.put(KeyboardProperties.KEY_LIMITED_CHARACTER_LIST, GENERAL_STRING_LIST);
	    	
	    	// Json StartTime Setup
			JSON_STARTTIME.put(StartTime.KEY_HOURS, GENERAL_STARTTIME.getHours());
			JSON_STARTTIME.put(StartTime.KEY_MINUTES, GENERAL_STARTTIME.getMinutes());
			JSON_STARTTIME.put(StartTime.KEY_SECONDS, GENERAL_STARTTIME.getSeconds());
			
			// Json Choice List Setup
			JSONObject jsonChoice = new JSONObject();
			jsonChoice.put(Choice.KEY_CHOICE_ID, GENERAL_INT);
			jsonChoice.put(Choice.KEY_IMAGE, JSON_IMAGE);
			jsonChoice.put(Choice.KEY_MENU_NAME, GENERAL_STRING);
			jsonChoice.put(Choice.KEY_SECONDARY_IMAGE, JSON_IMAGE);
			jsonChoice.put(Choice.KEY_SECONDARY_TEXT, GENERAL_STRING);
			jsonChoice.put(Choice.KEY_TERTIARY_TEXT, GENERAL_STRING);
			jsonChoice.put(Choice.KEY_VR_COMMANDS, GENERAL_STRING_LIST);
			JSON_CHOICES.put(jsonChoice);
			
			// Json DeviceInfo Setup
			JSON_DEVICEINFO.put(DeviceInfo.KEY_CARRIER, GENERAL_STRING);
			JSON_DEVICEINFO.put(DeviceInfo.KEY_FIRMWARE_REV, GENERAL_STRING);
			JSON_DEVICEINFO.put(DeviceInfo.KEY_HARDWARE, GENERAL_STRING);
			JSON_DEVICEINFO.put(DeviceInfo.KEY_MAX_NUMBER_RFCOMM_PORTS, GENERAL_INT);
			JSON_DEVICEINFO.put(DeviceInfo.KEY_OS, GENERAL_STRING);
			JSON_DEVICEINFO.put(DeviceInfo.KEY_OS_VERSION, GENERAL_STRING);
			
			// Json SdlMessageVersion Setup
			JSON_SDLMSGVERSION.put(SdlMsgVersion.KEY_MAJOR_VERSION, GENERAL_INT);
			JSON_SDLMSGVERSION.put(SdlMsgVersion.KEY_MINOR_VERSION, GENERAL_INT);			
		} catch (JSONException e) {
			Log.e("Test", "Static Json Construction Failed.", e);
		}
	}	
}