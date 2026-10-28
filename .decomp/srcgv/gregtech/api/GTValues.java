package gregtech.api;

import gregtech.api.util.random.XoShiRo256PlusPlusRandom;
import gregtech.common.ConfigHolder;
import java.time.LocalDate;
import java.util.Random;
import java.util.function.Supplier;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;

public class GTValues {
   public static final long M = 3628800L;
   public static final int L = 144;
   public static final short W = Short.MAX_VALUE;
   public static final Random RNG = new XoShiRo256PlusPlusRandom();
   public static long CLIENT_TIME = 0L;
   public static final long[] V = new long[]{8L, 32L, 128L, 512L, 2048L, 8192L, 32768L, 131072L, 524288L, 2097152L, 8388608L, 33554432L, 134217728L, 536870912L, 2147483648L};
   public static final int[] VH = new int[]{4, 16, 64, 256, 1024, 4096, 16384, 65536, 262144, 1048576, 4194304, 16777216, 67108864, 268435456, 1073741824};
   public static final int[] VA = new int[]{7, 30, 120, 480, 1920, 7680, 30720, 122880, 491520, 1966080, 7864320, 31457280, 125829120, 503316480, 2013265920};
   public static final int[] VHA = new int[]{4, 16, 60, 240, 960, 3840, 15360, 61440, 245760, 983040, 3932160, 15728640, 62914560, 251658240, 1006632960};
   public static final long[] VOC = new long[]{8L, 32L, 128L, 512L, 2048L, 8192L, 32768L, 131072L, 524288L, 2097152L, 8388608L, 33554432L, 134217728L, 536870912L, 2147483648L, 8589934592L, 34359738368L, 137438953472L, 549755813888L, 2199023255552L, 8796093022208L, 35184372088832L, 140737488355328L, 562949953421312L, 2251799813685248L, 9007199254740992L, 36028797018963968L, 144115188075855872L, 576460752303423488L, 2305843009213693952L, Long.MAX_VALUE};
   public static final long[] VAOC = new long[]{7L, 30L, 120L, 480L, 1920L, 7680L, 30720L, 123880L, 491520L, 1966080L, 7864320L, 31457280L, 125829120L, 503316480L, 2013265920L, 8053063680L, 32212254720L, 128849018880L, 515396075520L, 2061584302080L, 8246337208320L, 32985348833280L, 131948427333120L, 527865581332480L, 2111062325297920L, 8444249301319680L, 33777097205278720L, 135108988821415880L, 540431751284459520L, 2161727821137838080L, 8646911284550352320L};
   public static final int ULV = 0;
   public static final int LV = 1;
   public static final int MV = 2;
   public static final int HV = 3;
   public static final int EV = 4;
   public static final int IV = 5;
   public static final int LuV = 6;
   public static final int ZPM = 7;
   public static final int UV = 8;
   public static final int UHV = 9;
   public static final int UEV = 10;
   public static final int UIV = 11;
   public static final int UXV = 12;
   public static final int OpV = 13;
   public static final int MAX = 14;
   public static final int MAX_TRUE = 30;
   public static final String[] VN = new String[]{"ULV", "LV", "MV", "HV", "EV", "IV", "LuV", "ZPM", "UV", "UHV", "UEV", "UIV", "UXV", "OpV", "MAX"};
   public static final String[] VOCN = new String[]{"ULV", "LV", "MV", "HV", "EV", "IV", "LuV", "ZPM", "UV", "UHV", "UEV", "UIV", "UXV", "OpV", "MAX", "MAX+1", "MAX+2", "MAX+3", "MAX+4", "MAX+5", "MAX+6", "MAX+7", "MAX+8", "MAX+9", "MAX+10", "MAX+11", "MAX+12", "MAX+13", "MAX+14", "MAX+15", "MAX+16"};
   public static final String[] VNF;
   private static final String MAX_PLUS;
   public static final String[] VOCNF;
   public static final int[] VC;
   public static final String[] VOLTAGE_NAMES;
   public static final String MODID = "gregtech";
   public static final String MOD_NAME = "GregTech";
   private static Boolean isClient;
   private static Boolean isDeobf;
   public static final int FALLBACK = -1;
   public static Supplier<Boolean> FOOLS;
   public static Supplier<Boolean> XMAS;

   public static boolean isClientSide() {
      if (isClient == null) {
         isClient = FMLCommonHandler.instance().getSide().isClient();
      }

      return isClient;
   }

   public static boolean isDeobfEnvironment() {
      if (isDeobf == null) {
         isDeobf = FMLLaunchHandler.isDeobfuscatedEnvironment();
      }

      return isDeobf;
   }

   static {
      VNF = new String[]{TextFormatting.DARK_GRAY + "ULV", TextFormatting.GRAY + "LV", TextFormatting.AQUA + "MV", TextFormatting.GOLD + "HV", TextFormatting.DARK_PURPLE + "EV", TextFormatting.BLUE + "IV", TextFormatting.LIGHT_PURPLE + "LuV", TextFormatting.RED + "ZPM", TextFormatting.DARK_AQUA + "UV", TextFormatting.DARK_RED + "UHV", TextFormatting.GREEN + "UEV", TextFormatting.DARK_GREEN + "UIV", TextFormatting.YELLOW + "UXV", TextFormatting.BLUE.toString() + TextFormatting.BOLD + "OpV", TextFormatting.RED.toString() + TextFormatting.BOLD + "MAX"};
      MAX_PLUS = TextFormatting.RED.toString() + TextFormatting.BOLD + "M" + TextFormatting.YELLOW + TextFormatting.BOLD + "A" + TextFormatting.GREEN + TextFormatting.BOLD + "X" + TextFormatting.AQUA + TextFormatting.BOLD + "+" + TextFormatting.LIGHT_PURPLE + TextFormatting.BOLD;
      VOCNF = new String[]{TextFormatting.DARK_GRAY + "ULV", TextFormatting.GRAY + "LV", TextFormatting.AQUA + "MV", TextFormatting.GOLD + "HV", TextFormatting.DARK_PURPLE + "EV", TextFormatting.BLUE + "IV", TextFormatting.LIGHT_PURPLE + "LuV", TextFormatting.RED + "ZPM", TextFormatting.DARK_AQUA + "UV", TextFormatting.DARK_RED + "UHV", TextFormatting.GREEN + "UEV", TextFormatting.DARK_GREEN + "UIV", TextFormatting.YELLOW + "UXV", TextFormatting.BLUE.toString() + TextFormatting.BOLD + "OpV", TextFormatting.RED.toString() + TextFormatting.BOLD + "MAX", MAX_PLUS + "1", MAX_PLUS + "2", MAX_PLUS + "3", MAX_PLUS + "4", MAX_PLUS + "5", MAX_PLUS + "6", MAX_PLUS + "7", MAX_PLUS + "8", MAX_PLUS + "9", MAX_PLUS + "10", MAX_PLUS + "11", MAX_PLUS + "12", MAX_PLUS + "13", MAX_PLUS + "14", MAX_PLUS + "15", MAX_PLUS + "16"};
      VC = new int[]{13107200, 14474460, 16737280, 16776990, 8421504, 15790325, 15308695, 8307652, 8302718, 12547264, 744702, 9522833, 4753224, 9175040, 2631925};
      VOLTAGE_NAMES = new String[]{"Ultra Low Voltage", "Low Voltage", "Medium Voltage", "High Voltage", "Extreme Voltage", "Insane Voltage", "Ludicrous Voltage", "ZPM Voltage", "Ultimate Voltage", "Ultra High Voltage", "Ultra Excessive Voltage", "Ultra Immense Voltage", "Ultra Extreme Voltage", "Overpowered Voltage", "Maximum Voltage"};
      FOOLS = () -> {
         String[] yearMonthDay = LocalDate.now().toString().split("-");
         return ConfigHolder.misc.specialEvents && yearMonthDay[1].equals("04") && yearMonthDay[2].equals("01");
      };
      XMAS = () -> {
         String[] yearMonthDay = LocalDate.now().toString().split("-");
         return ConfigHolder.misc.specialEvents && yearMonthDay[1].equals("12") && (yearMonthDay[2].equals("24") || yearMonthDay[2].equals("25"));
      };
   }
}
