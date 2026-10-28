package gregtechlite.gtlitecore.api.unification.material;

import gregtech.api.GTValues;
import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.attribute.FluidAttribute;
import gregtech.api.fluids.attribute.FluidAttributes;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.MaterialToolProperty;
import gregtech.api.unification.material.properties.BlastProperty.GasTier;
import gregtechlite.gtlitecore.api.GTLiteValues;
import gregtechlite.gtlitecore.api.extension.MaterialExtKt;
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials;
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialFlags;
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"},
   d2 = {"Lgregtechlite/gtlitecore/api/unification/material/GTLiteFirstDegreeMaterials;", "", "<init>", "()V", "init", "", "gtlitecore"}
)
public final class GTLiteFirstDegreeMaterials {
   @NotNull
   public static final GTLiteFirstDegreeMaterials INSTANCE = new GTLiteFirstDegreeMaterials();

   private GTLiteFirstDegreeMaterials() {
   }

   public final void init() {
      GTLiteMaterials.INSTANCE.setDolomite(GTMaterialBuilder.addMaterial(2001, "dolomite", GTLiteFirstDegreeMaterials::init$lambda$0));
      GTLiteMaterials.INSTANCE.setTanzanite(GTMaterialBuilder.addMaterial(2002, "tanzanite", GTLiteFirstDegreeMaterials::init$lambda$1));
      GTLiteMaterials.INSTANCE.setAzurite(GTMaterialBuilder.addMaterial(2003, "azurite", GTLiteFirstDegreeMaterials::init$lambda$2));
      GTLiteMaterials.INSTANCE.setForsterite(GTMaterialBuilder.addMaterial(2004, "forsterite", GTLiteFirstDegreeMaterials::init$lambda$3));
      GTLiteMaterials.INSTANCE.setAugite(GTMaterialBuilder.addMaterial(2005, "augite", GTLiteFirstDegreeMaterials::init$lambda$4));
      GTLiteMaterials.INSTANCE.setLizardite(GTMaterialBuilder.addMaterial(2006, "lizardite", GTLiteFirstDegreeMaterials::init$lambda$5));
      GTLiteMaterials.INSTANCE.setMuscovite(GTMaterialBuilder.addMaterial(2007, "muscovite", GTLiteFirstDegreeMaterials::init$lambda$6));
      GTLiteMaterials.INSTANCE.setClinochlore(GTMaterialBuilder.addMaterial(2008, "clinochlore", GTLiteFirstDegreeMaterials::init$lambda$7));
      GTLiteMaterials.INSTANCE.setAlbite(GTMaterialBuilder.addMaterial(2009, "albite", GTLiteFirstDegreeMaterials::init$lambda$8));
      GTLiteMaterials.INSTANCE.setFluorite(GTMaterialBuilder.addMaterial(2010, "fluorite", GTLiteFirstDegreeMaterials::init$lambda$9));
      GTLiteMaterials.INSTANCE.setAnorthite(GTMaterialBuilder.addMaterial(2011, "anorthite", GTLiteFirstDegreeMaterials::init$lambda$10));
      GTLiteMaterials.INSTANCE.setOligoclase(GTMaterialBuilder.addMaterial(2012, "oligoclase", GTLiteFirstDegreeMaterials::init$lambda$11));
      GTLiteMaterials.INSTANCE.setLabradorite(GTMaterialBuilder.addMaterial(2013, "labradorite", GTLiteFirstDegreeMaterials::init$lambda$12));
      GTLiteMaterials.INSTANCE.setBytownite(GTMaterialBuilder.addMaterial(2014, "bytownite", GTLiteFirstDegreeMaterials::init$lambda$13));
      GTLiteMaterials.INSTANCE.setTenorite(GTMaterialBuilder.addMaterial(2015, "tenorite", GTLiteFirstDegreeMaterials::init$lambda$14));
      GTLiteMaterials.INSTANCE.setCuprite(GTMaterialBuilder.addMaterial(2016, "cuprite", GTLiteFirstDegreeMaterials::init$lambda$15));
      GTLiteMaterials.INSTANCE.setWollastonite(GTMaterialBuilder.addMaterial(2017, "wollastonite", GTLiteFirstDegreeMaterials::init$lambda$16));
      GTLiteMaterials.INSTANCE.setFluorapatite(GTMaterialBuilder.addMaterial(2018, "fluorapatite", GTLiteFirstDegreeMaterials::init$lambda$17));
      GTLiteMaterials.INSTANCE.setKaolinite(GTMaterialBuilder.addMaterial(2019, "kaolinite", GTLiteFirstDegreeMaterials::init$lambda$18));
      GTLiteMaterials.INSTANCE.setLignite(GTMaterialBuilder.addMaterial(2020, "lignite", GTLiteFirstDegreeMaterials::init$lambda$19));
      GTLiteMaterials.INSTANCE.setFirestone(GTMaterialBuilder.addMaterial(2021, "firestone", GTLiteFirstDegreeMaterials::init$lambda$20));
      GTLiteMaterials.INSTANCE.setIron3Sulfate(GTMaterialBuilder.addMaterial(2022, "iron_sulfate", GTLiteFirstDegreeMaterials::init$lambda$21));
      GTLiteMaterials.INSTANCE.setCelestine(GTMaterialBuilder.addMaterial(2023, "celestine", GTLiteFirstDegreeMaterials::init$lambda$22));
      GTLiteMaterials.INSTANCE.setStrontianite(GTMaterialBuilder.addMaterial(2024, "strontianite", GTLiteFirstDegreeMaterials::init$lambda$23));
      GTLiteMaterials.INSTANCE.setStrontiumOxide(GTMaterialBuilder.addMaterial(2025, "strontium_oxide", GTLiteFirstDegreeMaterials::init$lambda$24));
      GTLiteMaterials.INSTANCE.setStrontiumSulfide(GTMaterialBuilder.addMaterial(2026, "strontium_sulfide", GTLiteFirstDegreeMaterials::init$lambda$25));
      GTLiteMaterials.INSTANCE.setAlumina(GTMaterialBuilder.addMaterial(2027, "alumina", GTLiteFirstDegreeMaterials::init$lambda$26));
      GTLiteMaterials.INSTANCE.setPhlogopite(GTMaterialBuilder.addMaterial(2028, "phlogopite", GTLiteFirstDegreeMaterials::init$lambda$27));
      GTLiteMaterials.INSTANCE.setBaddeleyite(GTMaterialBuilder.addMaterial(2029, "baddeleyite", GTLiteFirstDegreeMaterials::init$lambda$28));
      GTLiteMaterials.INSTANCE.setNephelite(GTMaterialBuilder.addMaterial(2030, "nephelite", GTLiteFirstDegreeMaterials::init$lambda$29));
      GTLiteMaterials.INSTANCE.setAegirine(GTMaterialBuilder.addMaterial(2031, "aegirine", GTLiteFirstDegreeMaterials::init$lambda$30));
      GTLiteMaterials.INSTANCE.setNiobiumPentoxide(GTMaterialBuilder.addMaterial(2032, "niobium_pentoxide", GTLiteFirstDegreeMaterials::init$lambda$31));
      GTLiteMaterials.INSTANCE.setTantalumPentoxide(GTMaterialBuilder.addMaterial(2033, "tantalum_pentoxide", GTLiteFirstDegreeMaterials::init$lambda$32));
      GTLiteMaterials.INSTANCE.setCalciumDifluoride(GTMaterialBuilder.addMaterial(2034, "calcium_difluoride", GTLiteFirstDegreeMaterials::init$lambda$33));
      GTLiteMaterials.INSTANCE.setManganeseDifluoride(GTMaterialBuilder.addMaterial(2035, "manganese_difluoride", GTLiteFirstDegreeMaterials::init$lambda$34));
      GTLiteMaterials.INSTANCE.setHeavyAlkaliChloridesSolution(GTMaterialBuilder.addMaterial(2036, "heavy_alkali_chlorides_solution", GTLiteFirstDegreeMaterials::init$lambda$35));
      GTLiteMaterials.INSTANCE.setTinDichloride(GTMaterialBuilder.addMaterial(2037, "tin_dichloride", GTLiteFirstDegreeMaterials::init$lambda$36));
      GTLiteMaterials.INSTANCE.setTinTetrachloride(GTMaterialBuilder.addMaterial(2038, "tin_tetrachloride", GTLiteFirstDegreeMaterials::init$lambda$37));
      GTLiteMaterials.INSTANCE.setCaesiumHexachlorotinate(GTMaterialBuilder.addMaterial(2039, "caesium_hexachlorotinate", GTLiteFirstDegreeMaterials::init$lambda$38));
      GTLiteMaterials.INSTANCE.setRubidiumHexachlorotinate(GTMaterialBuilder.addMaterial(2040, "rubidium_hexachlorotinate", GTLiteFirstDegreeMaterials::init$lambda$39));
      GTLiteMaterials.INSTANCE.setCryolite(GTMaterialBuilder.addMaterial(2041, "cryolite", GTLiteFirstDegreeMaterials::init$lambda$40));
      GTLiteMaterials.INSTANCE.setAluminiumHydroxide(GTMaterialBuilder.addMaterial(2042, "aluminium_hydroxide", GTLiteFirstDegreeMaterials::init$lambda$41));
      GTLiteMaterials.INSTANCE.setSodiumAluminate(GTMaterialBuilder.addMaterial(2043, "sodium_aluminate", GTLiteFirstDegreeMaterials::init$lambda$42));
      GTLiteMaterials.INSTANCE.setSodiumCarbonateSolution(GTMaterialBuilder.addMaterial(2044, "sodium_carbonate_solution", GTLiteFirstDegreeMaterials::init$lambda$43));
      GTLiteMaterials.INSTANCE.setAluminiumSulfate(GTMaterialBuilder.addMaterial(2045, "aluminium_sulfate", GTLiteFirstDegreeMaterials::init$lambda$44));
      GTLiteMaterials.INSTANCE.setZSM5(GTMaterialBuilder.addMaterial(2046, "zsm_5", GTLiteFirstDegreeMaterials::init$lambda$45));
      GTLiteMaterials.INSTANCE.setMolybdenumTrioxide(GTMaterialBuilder.addMaterial(2047, "molybdenum_trioxide", GTLiteFirstDegreeMaterials::init$lambda$46));
      GTLiteMaterials.INSTANCE.setMolybdenumFlue(GTMaterialBuilder.addMaterial(2048, "molybdenum_flue", GTLiteFirstDegreeMaterials::init$lambda$48));
      GTLiteMaterials.INSTANCE.setLeadDichloride(GTMaterialBuilder.addMaterial(2049, "lead_dichloride", GTLiteFirstDegreeMaterials::init$lambda$49));
      GTLiteMaterials.INSTANCE.setTraceRheniumFlue(GTMaterialBuilder.addMaterial(2050, "trace_rhenium_flue", GTLiteFirstDegreeMaterials::init$lambda$51));
      GTLiteMaterials.INSTANCE.setPerrhenicAcid(GTMaterialBuilder.addMaterial(2051, "perrhenic_acid", GTLiteFirstDegreeMaterials::init$lambda$52));
      GTLiteMaterials.INSTANCE.setAmmoniumPerrhenate(GTMaterialBuilder.addMaterial(2052, "ammonium_perrhenate", GTLiteFirstDegreeMaterials::init$lambda$53));
      GTLiteMaterials.INSTANCE.setJade(GTMaterialBuilder.addMaterial(2053, "jade", GTLiteFirstDegreeMaterials::init$lambda$54));
      GTLiteMaterials.INSTANCE.setJasper(GTMaterialBuilder.addMaterial(2054, "jasper", GTLiteFirstDegreeMaterials::init$lambda$55));
      GTLiteMaterials.INSTANCE.setPicotite(GTMaterialBuilder.addMaterial(2055, "picotite", GTLiteFirstDegreeMaterials::init$lambda$56));
      GTLiteMaterials.INSTANCE.setManganeseMonoxide(GTMaterialBuilder.addMaterial(2056, "manganese_monoxide", GTLiteFirstDegreeMaterials::init$lambda$57));
      GTLiteMaterials.INSTANCE.setLeadChromate(GTMaterialBuilder.addMaterial(2057, "lead_chromate", GTLiteFirstDegreeMaterials::init$lambda$58));
      GTLiteMaterials.INSTANCE.setLeadNitrate(GTMaterialBuilder.addMaterial(2058, "lead_nitrate", GTLiteFirstDegreeMaterials::init$lambda$59));
      GTLiteMaterials.INSTANCE.setCobaltAluminate(GTMaterialBuilder.addMaterial(2059, "cobalt_aluminate", GTLiteFirstDegreeMaterials::init$lambda$60));
      GTLiteMaterials.INSTANCE.setOrpiment(GTMaterialBuilder.addMaterial(2060, "orpiment", GTLiteFirstDegreeMaterials::init$lambda$61));
      GTLiteMaterials.INSTANCE.setSodiumChlorate(GTMaterialBuilder.addMaterial(2061, "sodium_chlorate", GTLiteFirstDegreeMaterials::init$lambda$62));
      GTLiteMaterials.INSTANCE.setSodiumPerchlorate(GTMaterialBuilder.addMaterial(2062, "sodium_perchlorate", GTLiteFirstDegreeMaterials::init$lambda$63));
      GTLiteMaterials.INSTANCE.setSodiumHypochlorite(GTMaterialBuilder.addMaterial(2063, "sodium_hypochlorite", GTLiteFirstDegreeMaterials::init$lambda$64));
      GTLiteMaterials.INSTANCE.setTungstenTrioxide(GTMaterialBuilder.addMaterial(2064, "tungsten_trioxide", GTLiteFirstDegreeMaterials::init$lambda$65));
      GTLiteMaterials.INSTANCE.setStrontiumFerrite(GTMaterialBuilder.addMaterial(2065, "strontium_ferrite", GTLiteFirstDegreeMaterials::init$lambda$66));
      GTLiteMaterials.INSTANCE.setTitaniumNitrate(GTMaterialBuilder.addMaterial(2066, "titanium_nitrate", GTLiteFirstDegreeMaterials::init$lambda$67));
      GTLiteMaterials.INSTANCE.setLithiumTitanate(GTMaterialBuilder.addMaterial(2067, "lithium_titanate", GTLiteFirstDegreeMaterials::init$lambda$69));
      GTLiteMaterials.INSTANCE.setPalladiumNitrate(GTMaterialBuilder.addMaterial(2068, "palladium_nitrate", GTLiteFirstDegreeMaterials::init$lambda$70));
      GTLiteMaterials.INSTANCE.setPalladiumAcetate(GTMaterialBuilder.addMaterial(2069, "palladium_acetate", GTLiteFirstDegreeMaterials::init$lambda$71));
      GTLiteMaterials.INSTANCE.setPalladiumLoadedRutileNanoparticles(GTMaterialBuilder.addMaterial(2070, "palladium_loaded_rutile_nanoparticles", GTLiteFirstDegreeMaterials::init$lambda$72));
      GTLiteMaterials.INSTANCE.setLithiumOxide(GTMaterialBuilder.addMaterial(2071, "lithium_oxide", GTLiteFirstDegreeMaterials::init$lambda$73));
      GTLiteMaterials.INSTANCE.setLithiumCarbonate(GTMaterialBuilder.addMaterial(2072, "lithium_carbonate", GTLiteFirstDegreeMaterials::init$lambda$74));
      GTLiteMaterials.INSTANCE.setBlueVitriol(GTMaterialBuilder.addMaterial(2073, "blue_vitriol", GTLiteFirstDegreeMaterials::init$lambda$75));
      GTLiteMaterials.INSTANCE.setSodiumTellurite(GTMaterialBuilder.addMaterial(2074, "sodium_tellurite", GTLiteFirstDegreeMaterials::init$lambda$76));
      GTLiteMaterials.INSTANCE.setSeleniumDioxide(GTMaterialBuilder.addMaterial(2075, "selenium_dioxide", GTLiteFirstDegreeMaterials::init$lambda$77));
      GTLiteMaterials.INSTANCE.setTelluriumDioxide(GTMaterialBuilder.addMaterial(2076, "tellurium_dioxide", GTLiteFirstDegreeMaterials::init$lambda$78));
      GTLiteMaterials.INSTANCE.setSelenousAcid(GTMaterialBuilder.addMaterial(2077, "selenous_acid", GTLiteFirstDegreeMaterials::init$lambda$79));
      GTLiteMaterials.INSTANCE.setAluminiumSelenide(GTMaterialBuilder.addMaterial(2078, "aluminium_selenide", GTLiteFirstDegreeMaterials::init$lambda$80));
      GTLiteMaterials.INSTANCE.setHydrogenSelenide(GTMaterialBuilder.addMaterial(2079, "hydrogen_selenide", GTLiteFirstDegreeMaterials::init$lambda$81));
      GTLiteMaterials.INSTANCE.setCadmiumBromide(GTMaterialBuilder.addMaterial(2080, "cadmium_bromide", GTLiteFirstDegreeMaterials::init$lambda$82));
      GTLiteMaterials.INSTANCE.setMagnesiumBromide(GTMaterialBuilder.addMaterial(2081, "magnesium_bromide", GTLiteFirstDegreeMaterials::init$lambda$83));
      GTLiteMaterials.INSTANCE.setHRAMagnesium(GTMaterialBuilder.addMaterial(2082, "hra_magnesium", GTLiteFirstDegreeMaterials::init$lambda$84));
      GTLiteMaterials.INSTANCE.setHydrobromicAcid(GTMaterialBuilder.addMaterial(2083, "hydrobromic_acid", GTLiteFirstDegreeMaterials::init$lambda$86));
      GTLiteMaterials.INSTANCE.setDimethylcadmium(GTMaterialBuilder.addMaterial(2084, "dimethylcadmium", GTLiteFirstDegreeMaterials::init$lambda$87));
      GTLiteMaterials.INSTANCE.setCadmiumSelenide(GTMaterialBuilder.addMaterial(2085, "cadmium_selenide", GTLiteFirstDegreeMaterials::init$lambda$88));
      GTLiteMaterials.INSTANCE.setPrasiolite(GTMaterialBuilder.addMaterial(2086, "prasiolite", GTLiteFirstDegreeMaterials::init$lambda$89));
      GTLiteMaterials.INSTANCE.setZBLANGlass(GTMaterialBuilder.addMaterial(2087, "zblan_glass", GTLiteFirstDegreeMaterials::init$lambda$90));
      GTLiteMaterials.INSTANCE.setErbiumDopedZBLANGlass(GTMaterialBuilder.addMaterial(2088, "erbium_doped_zblan_glass", GTLiteFirstDegreeMaterials::init$lambda$91));
      GTLiteMaterials.INSTANCE.setPraseodymiumDopedZBLANGlass(GTMaterialBuilder.addMaterial(2089, "praseodymium_doped_zblan_glass", GTLiteFirstDegreeMaterials::init$lambda$92));
      GTLiteMaterials.INSTANCE.setOzone(GTMaterialBuilder.addMaterial(2090, "ozone", GTLiteFirstDegreeMaterials::init$lambda$93));
      GTLiteMaterials.INSTANCE.setCubicZirconia(GTMaterialBuilder.addMaterial(2091, "cubic_zirconia", GTLiteFirstDegreeMaterials::init$lambda$94));
      GTLiteMaterials.INSTANCE.setBismuthTelluride(GTMaterialBuilder.addMaterial(2092, "bismuth_telluride", GTLiteFirstDegreeMaterials::init$lambda$95));
      GTLiteMaterials.INSTANCE.setMagnetoResonatic(GTMaterialBuilder.addMaterial(2093, "magneto_resonatic", GTLiteFirstDegreeMaterials::init$lambda$96));
      GTLiteMaterials.INSTANCE.setLanthanumOxide(GTMaterialBuilder.addMaterial(2094, "lanthanum_oxide", GTLiteFirstDegreeMaterials::init$lambda$97));
      GTLiteMaterials.INSTANCE.setCeriumOxide(GTMaterialBuilder.addMaterial(2095, "cerium_oxide", GTLiteFirstDegreeMaterials::init$lambda$98));
      GTLiteMaterials.INSTANCE.setPraseodymiumOxide(GTMaterialBuilder.addMaterial(2096, "praseodymium_oxide", GTLiteFirstDegreeMaterials::init$lambda$99));
      GTLiteMaterials.INSTANCE.setNeodymiumOxide(GTMaterialBuilder.addMaterial(2097, "neodymium_oxide", GTLiteFirstDegreeMaterials::init$lambda$100));
      GTLiteMaterials.INSTANCE.setSamariumOxide(GTMaterialBuilder.addMaterial(2098, "samarium_oxide", GTLiteFirstDegreeMaterials::init$lambda$101));
      GTLiteMaterials.INSTANCE.setEuropiumOxide(GTMaterialBuilder.addMaterial(2099, "europium_oxide", GTLiteFirstDegreeMaterials::init$lambda$102));
      GTLiteMaterials.INSTANCE.setGadoliniumOxide(GTMaterialBuilder.addMaterial(2100, "gadolinium_oxide", GTLiteFirstDegreeMaterials::init$lambda$103));
      GTLiteMaterials.INSTANCE.setTerbiumOxide(GTMaterialBuilder.addMaterial(2101, "terbium_oxide", GTLiteFirstDegreeMaterials::init$lambda$104));
      GTLiteMaterials.INSTANCE.setDysprosiumOxide(GTMaterialBuilder.addMaterial(2102, "dysprosium_oxide", GTLiteFirstDegreeMaterials::init$lambda$105));
      GTLiteMaterials.INSTANCE.setHolmiumOxide(GTMaterialBuilder.addMaterial(2103, "holmium_oxide", GTLiteFirstDegreeMaterials::init$lambda$106));
      GTLiteMaterials.INSTANCE.setErbiumOxide(GTMaterialBuilder.addMaterial(2104, "erbium_oxide", GTLiteFirstDegreeMaterials::init$lambda$107));
      GTLiteMaterials.INSTANCE.setThuliumOxide(GTMaterialBuilder.addMaterial(2105, "thulium_oxide", GTLiteFirstDegreeMaterials::init$lambda$108));
      GTLiteMaterials.INSTANCE.setYtterbiumOxide(GTMaterialBuilder.addMaterial(2106, "ytterbium_oxide", GTLiteFirstDegreeMaterials::init$lambda$109));
      GTLiteMaterials.INSTANCE.setLutetiumOxide(GTMaterialBuilder.addMaterial(2107, "lutetium_oxide", GTLiteFirstDegreeMaterials::init$lambda$110));
      GTLiteMaterials.INSTANCE.setScandiumOxide(GTMaterialBuilder.addMaterial(2108, "scandium_oxide", GTLiteFirstDegreeMaterials::init$lambda$111));
      GTLiteMaterials.INSTANCE.setYttriumOxide(GTMaterialBuilder.addMaterial(2109, "yttrium_oxide", GTLiteFirstDegreeMaterials::init$lambda$112));
      GTLiteMaterials.INSTANCE.setPromethiumOxide(GTMaterialBuilder.addMaterial(2110, "promethium_oxide", GTLiteFirstDegreeMaterials::init$lambda$113));
      GTLiteMaterials.INSTANCE.setLaPrNdCeOxidesSolution(GTMaterialBuilder.addMaterial(2111, "la_pr_nd_ce_oxides_solution", GTLiteFirstDegreeMaterials::init$lambda$114));
      GTLiteMaterials.INSTANCE.setScEuGdSmOxidesSolution(GTMaterialBuilder.addMaterial(2112, "sc_eu_gd_sm_oxides_solution", GTLiteFirstDegreeMaterials::init$lambda$115));
      GTLiteMaterials.INSTANCE.setYTbDyHoOxidesSolution(GTMaterialBuilder.addMaterial(2113, "y_tb_dy_ho_oxides_solution", GTLiteFirstDegreeMaterials::init$lambda$116));
      GTLiteMaterials.INSTANCE.setErTmYbLuOxidesSolution(GTMaterialBuilder.addMaterial(2114, "er_tm_yb_lu_oxides_solution", GTLiteFirstDegreeMaterials::init$lambda$117));
      GTLiteMaterials.INSTANCE.setPlatinumGroupResidue(GTMaterialBuilder.addMaterial(2115, "platinum_group_residue", GTLiteFirstDegreeMaterials::init$lambda$118));
      GTLiteMaterials.INSTANCE.setPlatinumGroupConcentrate(GTMaterialBuilder.addMaterial(2116, "platinum_group_concentrate", GTLiteFirstDegreeMaterials::init$lambda$119));
      GTLiteMaterials.INSTANCE.setPurifiedPlatinumGroupConcentrate(GTMaterialBuilder.addMaterial(2117, "purified_platinum_group_concentrate", GTLiteFirstDegreeMaterials::init$lambda$120));
      GTLiteMaterials.INSTANCE.setAmmoniumHexachloroplatinate(GTMaterialBuilder.addMaterial(2118, "ammonium_hexachloroplatinate", GTLiteFirstDegreeMaterials::init$lambda$121));
      GTLiteMaterials.INSTANCE.setAmmoniumHexachloropalladate(GTMaterialBuilder.addMaterial(2119, "ammonium_hexachloropalladate", GTLiteFirstDegreeMaterials::init$lambda$122));
      GTLiteMaterials.INSTANCE.setSodiumNitrate(GTMaterialBuilder.addMaterial(2120, "sodium_nitrate", GTLiteFirstDegreeMaterials::init$lambda$123));
      GTLiteMaterials.INSTANCE.setHexachloroplatinicAcid(GTMaterialBuilder.addMaterial(2121, "hexachloroplatinic_acid", GTLiteFirstDegreeMaterials::init$lambda$125));
      GTLiteMaterials.INSTANCE.setCarbonTetrachloride(GTMaterialBuilder.addMaterial(2122, "carbon_tetrachloride", GTLiteFirstDegreeMaterials::init$lambda$126));
      GTLiteMaterials.INSTANCE.setSodiumPeroxide(GTMaterialBuilder.addMaterial(2123, "sodium_peroxide", GTLiteFirstDegreeMaterials::init$lambda$127));
      GTLiteMaterials.INSTANCE.setRutheniumTrichloride(GTMaterialBuilder.addMaterial(2124, "ruthenium_trichloride", GTLiteFirstDegreeMaterials::init$lambda$128));
      GTLiteMaterials.INSTANCE.setRhodiumTrioxide(GTMaterialBuilder.addMaterial(2125, "rhodium_trioxide", GTLiteFirstDegreeMaterials::init$lambda$129));
      GTLiteMaterials.INSTANCE.setSulfurDichloride(GTMaterialBuilder.addMaterial(2126, "sulfur_dichloride", GTLiteFirstDegreeMaterials::init$lambda$130));
      GTLiteMaterials.INSTANCE.setOsmiumTetrachloride(GTMaterialBuilder.addMaterial(2127, "osmium_tetrachloride", GTLiteFirstDegreeMaterials::init$lambda$131));
      GTLiteMaterials.INSTANCE.setBerylliumOxide(GTMaterialBuilder.addMaterial(2128, "beryllium_oxide", GTLiteFirstDegreeMaterials::init$lambda$132));
      GTLiteMaterials.INSTANCE.setHydrogenPeroxide(GTMaterialBuilder.addMaterial(2129, "hydrogen_peroxide", GTLiteFirstDegreeMaterials::init$lambda$133));
      GTLiteMaterials.INSTANCE.setGrapheneOxide(GTMaterialBuilder.addMaterial(2130, "graphene_oxide", GTLiteFirstDegreeMaterials::init$lambda$134));
      GTLiteMaterials.INSTANCE.setYttriumNitrate(GTMaterialBuilder.addMaterial(2131, "yttrium_nitrate", GTLiteFirstDegreeMaterials::init$lambda$135));
      GTLiteMaterials.INSTANCE.setBariumNitrate(GTMaterialBuilder.addMaterial(2132, "barium_nitrate", GTLiteFirstDegreeMaterials::init$lambda$136));
      GTLiteMaterials.INSTANCE.setCopperNitrate(GTMaterialBuilder.addMaterial(2133, "copper_nitrate", GTLiteFirstDegreeMaterials::init$lambda$137));
      GTLiteMaterials.INSTANCE.setYttriumBariumCopperOxidesMixture(GTMaterialBuilder.addMaterial(2134, "yttrium_barium_copper_oxides_mixture", GTLiteFirstDegreeMaterials::init$lambda$138));
      GTLiteMaterials.INSTANCE.setGSTGlass(GTMaterialBuilder.addMaterial(2135, "gst_glass", GTLiteFirstDegreeMaterials::init$lambda$139));
      GTLiteMaterials.INSTANCE.setGermaniumDioxide(GTMaterialBuilder.addMaterial(2136, "germanium_dioxide", GTLiteFirstDegreeMaterials::init$lambda$140));
      GTLiteMaterials.INSTANCE.setRoastedSphalerite(GTMaterialBuilder.addMaterial(2137, "roasted_sphalerite", GTLiteFirstDegreeMaterials::init$lambda$141));
      GTLiteMaterials.INSTANCE.setZincRichSphalerite(GTMaterialBuilder.addMaterial(2138, "zinc_rich_sphalerite", GTLiteFirstDegreeMaterials::init$lambda$142));
      GTLiteMaterials.INSTANCE.setWaelzOxide(GTMaterialBuilder.addMaterial(2139, "waelz_oxide", GTLiteFirstDegreeMaterials::init$lambda$143));
      GTLiteMaterials.INSTANCE.setWaelzSlag(GTMaterialBuilder.addMaterial(2140, "waelz_slag", GTLiteFirstDegreeMaterials::init$lambda$144));
      GTLiteMaterials.INSTANCE.setPiranhaSolution(GTMaterialBuilder.addMaterial(2141, "piranha_solution", GTLiteFirstDegreeMaterials::init$lambda$145));
      GTLiteMaterials.INSTANCE.setHydroxyquinolineAluminium(GTMaterialBuilder.addMaterial(2142, "hydroxyquinoline_aluminium", GTLiteFirstDegreeMaterials::init$lambda$146));
      GTLiteMaterials.INSTANCE.setHydroselenicAcid(GTMaterialBuilder.addMaterial(2143, "hydroselenic_acid", GTLiteFirstDegreeMaterials::init$lambda$148));
      GTLiteMaterials.INSTANCE.setCopperGalliumIndiumSelenide(GTMaterialBuilder.addMaterial(2144, "copper_gallium_indium_selenide", GTLiteFirstDegreeMaterials::init$lambda$149));
      GTLiteMaterials.INSTANCE.setBariumHydroxide(GTMaterialBuilder.addMaterial(2145, "barium_hydroxide", GTLiteFirstDegreeMaterials::init$lambda$150));
      GTLiteMaterials.INSTANCE.setBariumTitanate(GTMaterialBuilder.addMaterial(2146, "barium_titanate", GTLiteFirstDegreeMaterials::init$lambda$151));
      GTLiteMaterials.INSTANCE.setSamariumCobalt(GTMaterialBuilder.addMaterial(2147, "samarium_cobalt", GTLiteFirstDegreeMaterials::init$lambda$152));
      GTLiteMaterials.INSTANCE.setPotassiumHydroxide(GTMaterialBuilder.addMaterial(2148, "potassium_hydroxide", GTLiteFirstDegreeMaterials::init$lambda$154));
      GTLiteMaterials.INSTANCE.setCopperDichloride(GTMaterialBuilder.addMaterial(2149, "copper_dichloride", GTLiteFirstDegreeMaterials::init$lambda$155));
      GTLiteMaterials.INSTANCE.setSodiumCyanide(GTMaterialBuilder.addMaterial(2150, "sodium_cyanide", GTLiteFirstDegreeMaterials::init$lambda$156));
      GTLiteMaterials.INSTANCE.setPotassiumBromate(GTMaterialBuilder.addMaterial(2151, "potassium_bromate", GTLiteFirstDegreeMaterials::init$lambda$157));
      GTLiteMaterials.INSTANCE.setWhitePhosphorus(GTMaterialBuilder.addMaterial(2152, "white_phosphorus", GTLiteFirstDegreeMaterials::init$lambda$158));
      GTLiteMaterials.INSTANCE.setRedPhosphorus(GTMaterialBuilder.addMaterial(2153, "red_phosphorus", GTLiteFirstDegreeMaterials::init$lambda$159));
      GTLiteMaterials.INSTANCE.setVioletPhosphorus(GTMaterialBuilder.addMaterial(2154, "violet_phosphorus", GTLiteFirstDegreeMaterials::init$lambda$160));
      GTLiteMaterials.INSTANCE.setBlackPhosphorus(GTMaterialBuilder.addMaterial(2155, "black_phosphorus", GTLiteFirstDegreeMaterials::init$lambda$161));
      GTLiteMaterials.INSTANCE.setBluePhosphorus(GTMaterialBuilder.addMaterial(2156, "blue_phosphorus", GTLiteFirstDegreeMaterials::init$lambda$162));
      GTLiteMaterials.INSTANCE.setPhosphorusTrichloride(GTMaterialBuilder.addMaterial(2157, "phosphorus_trichloride", GTLiteFirstDegreeMaterials::init$lambda$163));
      GTLiteMaterials.INSTANCE.setSodiumFluoride(GTMaterialBuilder.addMaterial(2158, "sodium_fluoride", GTLiteFirstDegreeMaterials::init$lambda$164));
      GTLiteMaterials.INSTANCE.setSodiumTrifluoroethanolate(GTMaterialBuilder.addMaterial(2159, "sodium_trifluoroethanolate", GTLiteFirstDegreeMaterials::init$lambda$165));
      GTLiteMaterials.INSTANCE.setTechnetiumHeptaoxide(GTMaterialBuilder.addMaterial(2160, "technetium_heptaoxide", GTLiteFirstDegreeMaterials::init$lambda$166));
      GTLiteMaterials.INSTANCE.setTriniumTrioxide(GTMaterialBuilder.addMaterial(2161, "trinium_trioxide", GTLiteFirstDegreeMaterials::init$lambda$167));
      GTLiteMaterials.INSTANCE.setPertechnetate(GTMaterialBuilder.addMaterial(2162, "pertechnetate", GTLiteFirstDegreeMaterials::init$lambda$169));
      GTLiteMaterials.INSTANCE.setAmmoniumNitrate(GTMaterialBuilder.addMaterial(2163, "ammonium_nitrate", GTLiteFirstDegreeMaterials::init$lambda$170));
      GTLiteMaterials.INSTANCE.setAmmoniumPertechnetate(GTMaterialBuilder.addMaterial(2164, "ammonium_pertechnetate", GTLiteFirstDegreeMaterials::init$lambda$171));
      GTLiteMaterials.INSTANCE.setTechnetiumDioxide(GTMaterialBuilder.addMaterial(2165, "technetium_dioxide", GTLiteFirstDegreeMaterials::init$lambda$172));
      GTLiteMaterials.INSTANCE.setIndiumPhosphate(GTMaterialBuilder.addMaterial(2166, "indium_phosphate", GTLiteFirstDegreeMaterials::init$lambda$173));
      GTLiteMaterials.INSTANCE.setGalliumDioxide(GTMaterialBuilder.addMaterial(2167, "gallium_dioxide", GTLiteFirstDegreeMaterials::init$lambda$174));
      GTLiteMaterials.INSTANCE.setCalciumSulfide(GTMaterialBuilder.addMaterial(2168, "calcium_sulfide", GTLiteFirstDegreeMaterials::init$lambda$175));
      GTLiteMaterials.INSTANCE.setRP1RocketFuel(GTMaterialBuilder.addMaterial(2169, "rp_1_rocket_fuel", GTLiteFirstDegreeMaterials::init$lambda$176));
      GTLiteMaterials.INSTANCE.setBismuthTrioxide(GTMaterialBuilder.addMaterial(2170, "bismuth_trioxide", GTLiteFirstDegreeMaterials::init$lambda$177));
      GTLiteMaterials.INSTANCE.setBismuthStrontiumCalciumCuprate(GTMaterialBuilder.addMaterial(2171, "bismuth_strontium_calcium_cuprate", GTLiteFirstDegreeMaterials::init$lambda$178));
      GTLiteMaterials.INSTANCE.setBedrockium(GTMaterialBuilder.addMaterial(2172, "bedrockium", GTLiteFirstDegreeMaterials::init$lambda$179));
      GTLiteMaterials.INSTANCE.setAdamantite(GTMaterialBuilder.addMaterial(2173, "adamantite", GTLiteFirstDegreeMaterials::init$lambda$180));
      GTLiteMaterials.INSTANCE.setAdamantiumUnstable(GTMaterialBuilder.addMaterial(2174, "adamantium_unstable", GTLiteFirstDegreeMaterials::init$lambda$181));
      GTLiteMaterials.INSTANCE.setAdamantiumEnriched(GTMaterialBuilder.addMaterial(2175, "adamantium_enriched", GTLiteFirstDegreeMaterials::init$lambda$182));
      GTLiteMaterials.INSTANCE.setVibraniumUnstable(GTMaterialBuilder.addMaterial(2176, "vibranium_unstable", GTLiteFirstDegreeMaterials::init$lambda$183));
      GTLiteMaterials.INSTANCE.setDeepIron(GTMaterialBuilder.addMaterial(2177, "deep_iron", GTLiteFirstDegreeMaterials::init$lambda$184));
      GTLiteMaterials.INSTANCE.setSodiumPotassiumEutatic(GTMaterialBuilder.addMaterial(2178, "sodium_potassium_eutatic", GTLiteFirstDegreeMaterials::init$lambda$185));
      GTLiteMaterials.INSTANCE.setLeadBismuthEutatic(GTMaterialBuilder.addMaterial(2179, "lead_bismuth_eutatic", GTLiteFirstDegreeMaterials::init$lambda$186));
      GTLiteMaterials.INSTANCE.setLithiumFluoride(GTMaterialBuilder.addMaterial(2180, "lithium_fluoride", GTLiteFirstDegreeMaterials::init$lambda$187));
      GTLiteMaterials.INSTANCE.setPotassiumFluoride(GTMaterialBuilder.addMaterial(2181, "potassium_fluoride", GTLiteFirstDegreeMaterials::init$lambda$188));
      GTLiteMaterials.INSTANCE.setLithiumSodiumPotassiumFluorides(GTMaterialBuilder.addMaterial(2182, "lithium_sodium_potassium_fluorides", GTLiteFirstDegreeMaterials::init$lambda$189));
      GTLiteMaterials.INSTANCE.setBerylliumDifluoride(GTMaterialBuilder.addMaterial(2183, "beryllium_difluoride", GTLiteFirstDegreeMaterials::init$lambda$190));
      GTLiteMaterials.INSTANCE.setLithiumBerylliumFluorides(GTMaterialBuilder.addMaterial(2184, "lithium_beryllium_fluorides", GTLiteFirstDegreeMaterials::init$lambda$191));
      GTLiteMaterials.INSTANCE.setFleroviumYtterbiumPlasma(GTMaterialBuilder.addMaterial(2185, "flerovium_ytterbium_plasma", GTLiteFirstDegreeMaterials::init$lambda$193));
      GTLiteMaterials.INSTANCE.setRubidiumTitanate(GTMaterialBuilder.addMaterial(2186, "rubidium_titanate", GTLiteFirstDegreeMaterials::init$lambda$194));
      GTLiteMaterials.INSTANCE.setSodiumTitanate(GTMaterialBuilder.addMaterial(2187, "sodium_titanate", GTLiteFirstDegreeMaterials::init$lambda$195));
      GTLiteMaterials.INSTANCE.setRubidiumChloride(GTMaterialBuilder.addMaterial(2188, "rubidium_chloride", GTLiteFirstDegreeMaterials::init$lambda$196));
      GTLiteMaterials.INSTANCE.setSodiumOxide(GTMaterialBuilder.addMaterial(2189, "sodium_oxide", GTLiteFirstDegreeMaterials::init$lambda$197));
      GTLiteMaterials.INSTANCE.setSodiumAcetate(GTMaterialBuilder.addMaterial(2190, "sodium_acetate", GTLiteFirstDegreeMaterials::init$lambda$198));
      GTLiteMaterials.INSTANCE.setCaesiumBromide(GTMaterialBuilder.addMaterial(2191, "caesium_bromide", GTLiteFirstDegreeMaterials::init$lambda$199));
      GTLiteMaterials.INSTANCE.setFranciumBromide(GTMaterialBuilder.addMaterial(2192, "francium_bromide", GTLiteFirstDegreeMaterials::init$lambda$200));
      GTLiteMaterials.INSTANCE.setFranciumCaesiumCadmiumBromide(GTMaterialBuilder.addMaterial(2193, "francium_caesium_cadmium_bromide", GTLiteFirstDegreeMaterials::init$lambda$201));
      GTLiteMaterials.INSTANCE.setSodiumSeaborgate(GTMaterialBuilder.addMaterial(2194, "sodium_seaborgate", GTLiteFirstDegreeMaterials::init$lambda$202));
      GTLiteMaterials.INSTANCE.setLeadScandiumTantalate(GTMaterialBuilder.addMaterial(2195, "lead_scandium_tantalate", GTLiteFirstDegreeMaterials::init$lambda$203));
      GTLiteMaterials.INSTANCE.setThalliumSulfate(GTMaterialBuilder.addMaterial(2196, "thallium_sulfate", GTLiteFirstDegreeMaterials::init$lambda$204));
      GTLiteMaterials.INSTANCE.setThalliumBariumCalciumCuprate(GTMaterialBuilder.addMaterial(2197, "thallium_barium_calcium_cuprate", GTLiteFirstDegreeMaterials::init$lambda$205));
      GTLiteMaterials.INSTANCE.setPotassiumManganate(GTMaterialBuilder.addMaterial(2198, "potassium_manganate", GTLiteFirstDegreeMaterials::init$lambda$206));
      GTLiteMaterials.INSTANCE.setPotassiumPermanganate(GTMaterialBuilder.addMaterial(2199, "potassium_permanganate", GTLiteFirstDegreeMaterials::init$lambda$207));
      GTLiteMaterials.INSTANCE.setLanthanumGalliumManganate(GTMaterialBuilder.addMaterial(2200, "lanthanum_gallium_manganate", GTLiteFirstDegreeMaterials::init$lambda$208));
      GTLiteMaterials.INSTANCE.setBariumStrontiumTitanate(GTMaterialBuilder.addMaterial(2201, "barium_strontium_titanate", GTLiteFirstDegreeMaterials::init$lambda$209));
      GTLiteMaterials.INSTANCE.setLutetiumManganeseGermanium(GTMaterialBuilder.addMaterial(2202, "lutetium_manganese_germanium", GTLiteFirstDegreeMaterials::init$lambda$210));
      GTLiteMaterials.INSTANCE.setBoricAcid(GTMaterialBuilder.addMaterial(2203, "boric_acid", GTLiteFirstDegreeMaterials::init$lambda$211));
      GTLiteMaterials.INSTANCE.setBoronTrioxide(GTMaterialBuilder.addMaterial(2204, "boron_trioxide", GTLiteFirstDegreeMaterials::init$lambda$212));
      GTLiteMaterials.INSTANCE.setBoronTrifluoride(GTMaterialBuilder.addMaterial(2205, "boron_trifluoride", GTLiteFirstDegreeMaterials::init$lambda$213));
      GTLiteMaterials.INSTANCE.setLithiumHydride(GTMaterialBuilder.addMaterial(2206, "lithium_hydride", GTLiteFirstDegreeMaterials::init$lambda$214));
      GTLiteMaterials.INSTANCE.setLithiumTetrafluoroborate(GTMaterialBuilder.addMaterial(2207, "lithium_tetrafluoroborate", GTLiteFirstDegreeMaterials::init$lambda$215));
      GTLiteMaterials.INSTANCE.setBoronTrichloride(GTMaterialBuilder.addMaterial(2208, "boron_trichloride", GTLiteFirstDegreeMaterials::init$lambda$216));
      GTLiteMaterials.INSTANCE.setHexagonalBoronNitride(GTMaterialBuilder.addMaterial(2209, "hexagonal_boron_nitride", GTLiteFirstDegreeMaterials::init$lambda$217));
      GTLiteMaterials.INSTANCE.setCubicBoronNitride(GTMaterialBuilder.addMaterial(2210, "cubic_boron_nitride", GTLiteFirstDegreeMaterials::init$lambda$218));
      GTLiteMaterials.INSTANCE.setAmorphousBoronNitride(GTMaterialBuilder.addMaterial(2211, "amorphous_boron_nitride", GTLiteFirstDegreeMaterials::init$lambda$219));
      GTLiteMaterials.INSTANCE.setHeterodiamond(GTMaterialBuilder.addMaterial(2212, "heterodiamond", GTLiteFirstDegreeMaterials::init$lambda$220));
      GTLiteMaterials.INSTANCE.setCubicHeterodiamond(GTMaterialBuilder.addMaterial(2213, "cubic_heterodiamond", GTLiteFirstDegreeMaterials::init$lambda$221));
      GTLiteMaterials.INSTANCE.setCalciumCarbide(GTMaterialBuilder.addMaterial(2214, "calcium_carbide", GTLiteFirstDegreeMaterials::init$lambda$222));
      GTLiteMaterials.INSTANCE.setCalciumHydroxide(GTMaterialBuilder.addMaterial(2215, "calcium_hydroxide", GTLiteFirstDegreeMaterials::init$lambda$223));
      GTLiteMaterials.INSTANCE.setChromiumGermaniumTelluride(GTMaterialBuilder.addMaterial(2216, "chromium_germanium_telluride", GTLiteFirstDegreeMaterials::init$lambda$224));
      GTLiteMaterials.INSTANCE.setChromiumGermaniumTellurideMagnetic(GTMaterialBuilder.addMaterial(2217, "chromium_germanium_telluride_magnetic", GTLiteFirstDegreeMaterials::init$lambda$225));
      GTLiteMaterials.INSTANCE.setGalliumTrichloride(GTMaterialBuilder.addMaterial(2218, "gallium_trichloride", GTLiteFirstDegreeMaterials::init$lambda$226));
      GTLiteMaterials.INSTANCE.setGalliumTrioxide(GTMaterialBuilder.addMaterial(2219, "gallium_trioxide", GTLiteFirstDegreeMaterials::init$lambda$227));
      GTLiteMaterials.INSTANCE.setGalliumNitride(GTMaterialBuilder.addMaterial(2220, "gallium_nitride", GTLiteFirstDegreeMaterials::init$lambda$228));
      GTLiteMaterials.INSTANCE.setAluminiumTrichloride(GTMaterialBuilder.addMaterial(2221, "aluminium_trichloride", GTLiteFirstDegreeMaterials::init$lambda$229));
      GTLiteMaterials.INSTANCE.setNiobiumPentachloride(GTMaterialBuilder.addMaterial(2222, "niobium_pentachloride", GTLiteFirstDegreeMaterials::init$lambda$230));
      GTLiteMaterials.INSTANCE.setLithiumNiobate(GTMaterialBuilder.addMaterial(2223, "lithium_niobate", GTLiteFirstDegreeMaterials::init$lambda$231));
      GTLiteMaterials.INSTANCE.setManganeseSulfate(GTMaterialBuilder.addMaterial(2224, "manganese_sulfate", GTLiteFirstDegreeMaterials::init$lambda$232));
      GTLiteMaterials.INSTANCE.setPotassiumSulfate(GTMaterialBuilder.addMaterial(2225, "potassium_sulfate", GTLiteFirstDegreeMaterials::init$lambda$233));
      GTLiteMaterials.INSTANCE.setNeodymiumDopedYttriumOxide(GTMaterialBuilder.addMaterial(2226, "neodymium_doped_yttrium_oxide", GTLiteFirstDegreeMaterials::init$lambda$234));
      GTLiteMaterials.INSTANCE.setAluminiumNitrate(GTMaterialBuilder.addMaterial(2227, "aluminium_nitrate", GTLiteFirstDegreeMaterials::init$lambda$235));
      GTLiteMaterials.INSTANCE.setChlorinatedSolvents(GTMaterialBuilder.addMaterial(2228, "chlorinated_solvents", GTLiteFirstDegreeMaterials::init$lambda$236));
      GTLiteMaterials.INSTANCE.setAluminaSolution(GTMaterialBuilder.addMaterial(2229, "alumina_solution", GTLiteFirstDegreeMaterials::init$lambda$237));
      GTLiteMaterials.INSTANCE.setNdYAG(GTMaterialBuilder.addMaterial(2230, "nd_yag", GTLiteFirstDegreeMaterials::init$lambda$238));
      GTLiteMaterials.INSTANCE.setScandiumTitaniumMixture(GTMaterialBuilder.addMaterial(2231, "scandium_titanium_mixture", GTLiteFirstDegreeMaterials::init$lambda$240));
      GTLiteMaterials.INSTANCE.setRadiumRadonMixture(GTMaterialBuilder.addMaterial(2232, "radium_radon_mixture", GTLiteFirstDegreeMaterials::init$lambda$242));
      GTLiteMaterials.INSTANCE.setPoloniumNitrate(GTMaterialBuilder.addMaterial(2233, "polonium_nitrate", GTLiteFirstDegreeMaterials::init$lambda$243));
      GTLiteMaterials.INSTANCE.setSodiumPolonate(GTMaterialBuilder.addMaterial(2234, "sodium_polonate", GTLiteFirstDegreeMaterials::init$lambda$244));
      GTLiteMaterials.INSTANCE.setPoloniumDioxide(GTMaterialBuilder.addMaterial(2235, "polonium_dioxide", GTLiteFirstDegreeMaterials::init$lambda$245));
      GTLiteMaterials.INSTANCE.setUranylChlorideSolution(GTMaterialBuilder.addMaterial(2236, "uranyl_chloride_solution", GTLiteFirstDegreeMaterials::init$lambda$246));
      GTLiteMaterials.INSTANCE.setUranylNitrateSolution(GTMaterialBuilder.addMaterial(2237, "uranyl_nitrate_solution", GTLiteFirstDegreeMaterials::init$lambda$247));
      GTLiteMaterials.INSTANCE.setRadiumSulfate(GTMaterialBuilder.addMaterial(2238, "radium_sulfate", GTLiteFirstDegreeMaterials::init$lambda$248));
      GTLiteMaterials.INSTANCE.setLeadSulfate(GTMaterialBuilder.addMaterial(2239, "lead_sulfate", GTLiteFirstDegreeMaterials::init$lambda$249));
      GTLiteMaterials.INSTANCE.setThoriumNitrate(GTMaterialBuilder.addMaterial(2240, "thorium_nitrate", GTLiteFirstDegreeMaterials::init$lambda$250));
      GTLiteMaterials.INSTANCE.setRadiumDichloride(GTMaterialBuilder.addMaterial(2241, "radium_dichloride", GTLiteFirstDegreeMaterials::init$lambda$251));
      GTLiteMaterials.INSTANCE.setThoriumDioxide(GTMaterialBuilder.addMaterial(2242, "thorium_dioxide", GTLiteFirstDegreeMaterials::init$lambda$252));
      GTLiteMaterials.INSTANCE.setUranylNitrate(GTMaterialBuilder.addMaterial(2243, "uranyl_nitrate", GTLiteFirstDegreeMaterials::init$lambda$253));
      GTLiteMaterials.INSTANCE.setBariumOxide(GTMaterialBuilder.addMaterial(2244, "barium_oxide", GTLiteFirstDegreeMaterials::init$lambda$254));
      GTLiteMaterials.INSTANCE.setWoodsGlass(GTMaterialBuilder.addMaterial(2245, "woods_glass", GTLiteFirstDegreeMaterials::init$lambda$255));
      GTLiteMaterials.INSTANCE.setPotassiumTertbutoxide(GTMaterialBuilder.addMaterial(2246, "potassium_tertbutoxide", GTLiteFirstDegreeMaterials::init$lambda$256));
      GTLiteMaterials.INSTANCE.setCaesiumHydroxide(GTMaterialBuilder.addMaterial(2247, "caesium_hydroxide", GTLiteFirstDegreeMaterials::init$lambda$257));
      GTLiteMaterials.INSTANCE.setCaesiumCarbonate(GTMaterialBuilder.addMaterial(2248, "caesium_carbonate", GTLiteFirstDegreeMaterials::init$lambda$258));
      GTLiteMaterials.INSTANCE.setSodiumHydride(GTMaterialBuilder.addMaterial(2249, "sodium_hydride", GTLiteFirstDegreeMaterials::init$lambda$259));
      GTLiteMaterials.INSTANCE.setVanadiumPentoxide(GTMaterialBuilder.addMaterial(2250, "vanadium_pentoxide", GTLiteFirstDegreeMaterials::init$lambda$260));
      GTLiteMaterials.INSTANCE.setActiniumOxalate(GTMaterialBuilder.addMaterial(2251, "actinium_oxalate", GTLiteFirstDegreeMaterials::init$lambda$261));
      GTLiteMaterials.INSTANCE.setActiniumTrihydride(GTMaterialBuilder.addMaterial(2252, "actinium_trihydride", GTLiteFirstDegreeMaterials::init$lambda$262));
      GTLiteMaterials.INSTANCE.setActiniumSuperhydride(GTMaterialBuilder.addMaterial(2253, "actinium_superhydride", GTLiteFirstDegreeMaterials::init$lambda$264));
      GTLiteMaterials.INSTANCE.setSodiumFormate(GTMaterialBuilder.addMaterial(2254, "sodium_formate", GTLiteFirstDegreeMaterials::init$lambda$265));
      GTLiteMaterials.INSTANCE.setSodiumThiosulfate(GTMaterialBuilder.addMaterial(2255, "sodium_thiosulfate", GTLiteFirstDegreeMaterials::init$lambda$266));
      GTLiteMaterials.INSTANCE.setLithiumThiinediselenide(GTMaterialBuilder.addMaterial(2256, "lithium_thiinediselenide", GTLiteFirstDegreeMaterials::init$lambda$267));
      GTLiteMaterials.INSTANCE.setBETSPerrhenate(GTMaterialBuilder.addMaterial(2257, "bets_perrhenate", GTLiteFirstDegreeMaterials::init$lambda$268));
      GTLiteMaterials.INSTANCE.setHeliumNeon(GTMaterialBuilder.addMaterial(2258, "helium_neon", GTLiteFirstDegreeMaterials::init$lambda$269));
      GTLiteMaterials.INSTANCE.setGermaniumTetrachloride(GTMaterialBuilder.addMaterial(2259, "germanium_tetrachloride", GTLiteFirstDegreeMaterials::init$lambda$270));
      GTLiteMaterials.INSTANCE.setSiliconTetrachloride(GTMaterialBuilder.addMaterial(2260, "silicon_tetrachloride", GTLiteFirstDegreeMaterials::init$lambda$271));
      GTLiteMaterials.INSTANCE.setCaesiumCeriumCobaltIndium(GTMaterialBuilder.addMaterial(2261, "caesium_cerium_cobalt_indium", GTLiteFirstDegreeMaterials::init$lambda$272));
      GTLiteMaterials.INSTANCE.setMercuryCadmiumTelluride(GTMaterialBuilder.addMaterial(2262, "mercury_cadmium_telluride", GTLiteFirstDegreeMaterials::init$lambda$273));
      GTLiteMaterials.INSTANCE.setThalliumRoentgeniumChloride(GTMaterialBuilder.addMaterial(2263, "thallium_roentgenium_chloride", GTLiteFirstDegreeMaterials::init$lambda$274));
      GTLiteMaterials.INSTANCE.setSilicaGelBase(GTMaterialBuilder.addMaterial(2264, "silica_gel_base", GTLiteFirstDegreeMaterials::init$lambda$275));
      GTLiteMaterials.INSTANCE.setSilicaGel(GTMaterialBuilder.addMaterial(2265, "silica_gel", GTLiteFirstDegreeMaterials::init$lambda$276));
      GTLiteMaterials.INSTANCE.setNitroniumTetrafluoroborate(GTMaterialBuilder.addMaterial(2266, "nitronium_tetrafluoroborate", GTLiteFirstDegreeMaterials::init$lambda$277));
      GTLiteMaterials.INSTANCE.setNitrosoniumTetrafluoroborate(GTMaterialBuilder.addMaterial(2267, "nitrosonium_tetrafluoroborate", GTLiteFirstDegreeMaterials::init$lambda$278));
      GTLiteMaterials.INSTANCE.setTetrafluoroboricAcid(GTMaterialBuilder.addMaterial(2268, "tetrafluoroboric_acid", GTLiteFirstDegreeMaterials::init$lambda$280));
      GTLiteMaterials.INSTANCE.setPotassiumFormate(GTMaterialBuilder.addMaterial(2269, "potassium_formate", GTLiteFirstDegreeMaterials::init$lambda$281));
      GTLiteMaterials.INSTANCE.setAmmoniumSulfate(GTMaterialBuilder.addMaterial(2270, "ammonium_sulfate", GTLiteFirstDegreeMaterials::init$lambda$282));
      GTLiteMaterials.INSTANCE.setAmmoniumCarbonate(GTMaterialBuilder.addMaterial(2271, "ammonium_carbonate", GTLiteFirstDegreeMaterials::init$lambda$283));
      GTLiteMaterials.INSTANCE.setAmmoniumAcetate(GTMaterialBuilder.addMaterial(2272, "ammonium_acetate", GTLiteFirstDegreeMaterials::init$lambda$284));
      GTLiteMaterials.INSTANCE.setSodiumAzanide(GTMaterialBuilder.addMaterial(2273, "sodium_azanide", GTLiteFirstDegreeMaterials::init$lambda$285));
      GTLiteMaterials.INSTANCE.setSodiumAzide(GTMaterialBuilder.addMaterial(2274, "sodium_azide", GTLiteFirstDegreeMaterials::init$lambda$286));
      GTLiteMaterials.INSTANCE.setPalladiumBisdibenzylidieneacetone(GTMaterialBuilder.addMaterial(2275, "palladium_bisdibenzylidieneacetone", GTLiteFirstDegreeMaterials::init$lambda$287));
      GTLiteMaterials.INSTANCE.setPotassiumTetrachloroplatinate(GTMaterialBuilder.addMaterial(2276, "potassium_tetrachloroplatinate", GTLiteFirstDegreeMaterials::init$lambda$288));
      GTLiteMaterials.INSTANCE.setAmmoniumPersulfate(GTMaterialBuilder.addMaterial(2277, "ammonium_persulfate", GTLiteFirstDegreeMaterials::init$lambda$289));
      GTLiteMaterials.INSTANCE.setSilverTetrafluoroborate(GTMaterialBuilder.addMaterial(2278, "silver_tetrafluoroborate", GTLiteFirstDegreeMaterials::init$lambda$290));
      GTLiteMaterials.INSTANCE.setSilverChloride(GTMaterialBuilder.addMaterial(2279, "silver_chloride", GTLiteFirstDegreeMaterials::init$lambda$291));
      GTLiteMaterials.INSTANCE.setSilverOxide(GTMaterialBuilder.addMaterial(2280, "silver_oxide", GTLiteFirstDegreeMaterials::init$lambda$292));
      GTLiteMaterials.INSTANCE.setHeavyQuarkEnrichedMixture(GTMaterialBuilder.addMaterial(2281, "heavy_quark_enriched_mixture", GTLiteFirstDegreeMaterials::init$lambda$294));
      GTLiteMaterials.INSTANCE.setDeuteriumSuperheavyMixture(GTMaterialBuilder.addMaterial(2282, "deuterium_superheavy_mixture", GTLiteFirstDegreeMaterials::init$lambda$296));
      GTLiteMaterials.INSTANCE.setHeavyQuarkDegenerateMatter(GTMaterialBuilder.addMaterial(2283, "heavy_quark_degenerate_matter", GTLiteFirstDegreeMaterials::init$lambda$298));
      GTLiteMaterials.INSTANCE.setQuantumchromodynamicallyConfinedMatter(GTMaterialBuilder.addMaterial(2284, "quantumchromodynamically_confined_matter", GTLiteFirstDegreeMaterials::init$lambda$299));
      GTLiteMaterials.INSTANCE.setSodiumEthoxide(GTMaterialBuilder.addMaterial(2285, "sodium_ethoxide", GTLiteFirstDegreeMaterials::init$lambda$300));
      GTLiteMaterials.INSTANCE.setIron2Chloride(GTMaterialBuilder.addMaterial(2286, "iron_2_chloride", GTLiteFirstDegreeMaterials::init$lambda$301));
      GTLiteMaterials.INSTANCE.setPalladiumFullereneMatrix(GTMaterialBuilder.addMaterial(2287, "palladium_fullerene_matrix", GTLiteFirstDegreeMaterials::init$lambda$302));
      GTLiteMaterials.INSTANCE.setLuTmYChloridesSolution(GTMaterialBuilder.addMaterial(2288, "lu_tm_y_chlorides_solution", GTLiteFirstDegreeMaterials::init$lambda$303));
      GTLiteMaterials.INSTANCE.setSodiumVanadate(GTMaterialBuilder.addMaterial(2289, "sodium_vanadate", GTLiteFirstDegreeMaterials::init$lambda$304));
      GTLiteMaterials.INSTANCE.setLuTmDopedYttriumVanadateDeposition(GTMaterialBuilder.addMaterial(2290, "lu_tm_doped_yttrium_vanadate_deposition", GTLiteFirstDegreeMaterials::init$lambda$305));
      GTLiteMaterials.INSTANCE.setLuTmYVO(GTMaterialBuilder.addMaterial(2291, "lu_tm_yvo", GTLiteFirstDegreeMaterials::init$lambda$306));
      GTLiteMaterials.INSTANCE.setHexagonalSiliconNitride(GTMaterialBuilder.addMaterial(2292, "hexagonal_silicon_nitride", GTLiteFirstDegreeMaterials::init$lambda$307));
      GTLiteMaterials.INSTANCE.setCubicSiliconNitride(GTMaterialBuilder.addMaterial(2293, "cubic_silicon_nitride", GTLiteFirstDegreeMaterials::init$lambda$308));
      GTLiteMaterials.INSTANCE.setCeriumCarbonate(GTMaterialBuilder.addMaterial(2294, "cerium_carbonate", GTLiteFirstDegreeMaterials::init$lambda$309));
      GTLiteMaterials.INSTANCE.setDielectricFormationMixture(GTMaterialBuilder.addMaterial(2295, "dielectric_formation_mixture", GTLiteFirstDegreeMaterials::init$lambda$311));
      GTLiteMaterials.INSTANCE.setChloroauricAcid(GTMaterialBuilder.addMaterial(2296, "chloroauric_acid", GTLiteFirstDegreeMaterials::init$lambda$313));
      GTLiteMaterials.INSTANCE.setCadmiumSulfide(GTMaterialBuilder.addMaterial(2297, "cadmium_sulfide", GTLiteFirstDegreeMaterials::init$lambda$314));
      GTLiteMaterials.INSTANCE.setBismuthChalcogenide(GTMaterialBuilder.addMaterial(2298, "bismuth_chalcogenide", GTLiteFirstDegreeMaterials::init$lambda$315));
      GTLiteMaterials.INSTANCE.setPlutoniumTrihydride(GTMaterialBuilder.addMaterial(2299, "plutonium_trihydride", GTLiteFirstDegreeMaterials::init$lambda$316));
      GTLiteMaterials.INSTANCE.setPlutoniumPhosphide(GTMaterialBuilder.addMaterial(2300, "plutonium_phosphide", GTLiteFirstDegreeMaterials::init$lambda$317));
      GTLiteMaterials.INSTANCE.setPhosphine(GTMaterialBuilder.addMaterial(2301, "phosphine", GTLiteFirstDegreeMaterials::init$lambda$318));
      GTLiteMaterials.INSTANCE.setMellion(GTMaterialBuilder.addMaterial(2302, "mellion", GTLiteFirstDegreeMaterials::init$lambda$319));
      GTLiteMaterials.INSTANCE.setLanthanumFullereneMixture(GTMaterialBuilder.addMaterial(2303, "lanthanum_fullerene_mixture", GTLiteFirstDegreeMaterials::init$lambda$320));
      GTLiteMaterials.INSTANCE.setLanthanumEmbeddedFullerene(GTMaterialBuilder.addMaterial(2304, "lanthanum_embedded_fullerene", GTLiteFirstDegreeMaterials::init$lambda$321));
      GTLiteMaterials.INSTANCE.setLanthanumFullereneNanotube(GTMaterialBuilder.addMaterial(2305, "lanthanum_fullerene_nanotube", GTLiteFirstDegreeMaterials::init$lambda$322));
      GTLiteMaterials.INSTANCE.setSeaborgiumDopedCarbonNanotube(GTMaterialBuilder.addMaterial(2306, "seaborgium_doped_carbon_nanotube", GTLiteFirstDegreeMaterials::init$lambda$323));
      GTLiteMaterials.INSTANCE.setPrHoYNitratesSolution(GTMaterialBuilder.addMaterial(2307, "pr_ho_y_nitrates_solution", GTLiteFirstDegreeMaterials::init$lambda$324));
      GTLiteMaterials.INSTANCE.setPrHoYLF(GTMaterialBuilder.addMaterial(2308, "pr_ho_ylf", GTLiteFirstDegreeMaterials::init$lambda$325));
      GTLiteMaterials.INSTANCE.setHarmonicPhononMatter(GTMaterialBuilder.addMaterial(2309, "harmonic_phonon_matter", GTLiteFirstDegreeMaterials::init$lambda$327));
      GTLiteMaterials.INSTANCE.setTrichlorocyclopentadienylTitanium(GTMaterialBuilder.addMaterial(2310, "trichlorocyclopentadienyl_titanium", GTLiteFirstDegreeMaterials::init$lambda$328));
      GTLiteMaterials.INSTANCE.setSilverPerchlorate(GTMaterialBuilder.addMaterial(2311, "silver_perchlorate", GTLiteFirstDegreeMaterials::init$lambda$329));
      GTLiteMaterials.INSTANCE.setPhotopolymerSolution(GTMaterialBuilder.addMaterial(2312, "photopolymer_solution", GTLiteFirstDegreeMaterials::init$lambda$330));
      GTLiteMaterials.INSTANCE.setSodiumBromide(GTMaterialBuilder.addMaterial(2313, "sodium_bromide", GTLiteFirstDegreeMaterials::init$lambda$331));
      GTLiteMaterials.INSTANCE.setBlackDwarfMatter(GTMaterialBuilder.addMaterial(2314, "black_dwarf_matter", GTLiteFirstDegreeMaterials::init$lambda$332));
      GTLiteMaterials.INSTANCE.setWhiteDwarfMatter(GTMaterialBuilder.addMaterial(2315, "white_dwarf_matter", GTLiteFirstDegreeMaterials::init$lambda$333));
      GTLiteMaterials.INSTANCE.setPotassiumFerrocyanideTrihydrate(GTMaterialBuilder.addMaterial(2316, "potassium_ferrocyanide_trihydrate", GTLiteFirstDegreeMaterials::init$lambda$334));
      GTLiteMaterials.INSTANCE.setPrussianBlue(GTMaterialBuilder.addMaterial(2317, "prussian_blue", GTLiteFirstDegreeMaterials::init$lambda$335));
      GTLiteMaterials.INSTANCE.setCopperArsenite(GTMaterialBuilder.addMaterial(2318, "copper_arsenite", GTLiteFirstDegreeMaterials::init$lambda$336));
      GTLiteMaterials.INSTANCE.setScheelesGreen(GTMaterialBuilder.addMaterial(2319, "scheeles_green", GTLiteFirstDegreeMaterials::init$lambda$337));
      GTLiteMaterials.INSTANCE.setCaesiumIodide(GTMaterialBuilder.addMaterial(2320, "caesium_iodide", GTLiteFirstDegreeMaterials::init$lambda$338));
      GTLiteMaterials.INSTANCE.setThalliumThuliumDopedCaesiumIodide(GTMaterialBuilder.addMaterial(2321, "thallium_thulium_doped_caesium_iodide", GTLiteFirstDegreeMaterials::init$lambda$339));
      GTLiteMaterials.INSTANCE.setCadmiumTungstate(GTMaterialBuilder.addMaterial(2322, "cadmium_tungstate", GTLiteFirstDegreeMaterials::init$lambda$340));
      GTLiteMaterials.INSTANCE.setBismuthGermanate(GTMaterialBuilder.addMaterial(2323, "bismuth_germanate", GTLiteFirstDegreeMaterials::init$lambda$341));
      GTLiteMaterials.INSTANCE.setLanthanumZirconate(GTMaterialBuilder.addMaterial(2324, "lanthanum_zirconate", GTLiteFirstDegreeMaterials::init$lambda$342));
      GTLiteMaterials.INSTANCE.setMoscoviumIridiate(GTMaterialBuilder.addMaterial(2325, "moscovium_iridiate", GTLiteFirstDegreeMaterials::init$lambda$343));
      GTLiteMaterials.INSTANCE.setHydromoscovicAcid(GTMaterialBuilder.addMaterial(2326, "hydromoscovic_acid", GTLiteFirstDegreeMaterials::init$lambda$345));
      GTLiteMaterials.INSTANCE.setStrontiumEuropiumNihonate(GTMaterialBuilder.addMaterial(2327, "strontium_europium_nihonate", GTLiteFirstDegreeMaterials::init$lambda$346));
      GTLiteMaterials.INSTANCE.setNeutroniumDopedCarbonNanotube(GTMaterialBuilder.addMaterial(2328, "neutronium_doped_carbon_nanotube", GTLiteFirstDegreeMaterials::init$lambda$347));
      GTLiteMaterials.INSTANCE.setCosmicFabric(GTMaterialBuilder.addMaterial(2329, "cosmic_fabric", GTLiteFirstDegreeMaterials::init$lambda$348));
      GTLiteMaterials.INSTANCE.setSiliconNanoparticles(GTMaterialBuilder.addMaterial(2330, "silicon_nanoparticles", GTLiteFirstDegreeMaterials::init$lambda$349));
      GTLiteMaterials.INSTANCE.setSodiumAlginate(GTMaterialBuilder.addMaterial(2331, "sodium_alginate", GTLiteFirstDegreeMaterials::init$lambda$350));
      GTLiteMaterials.INSTANCE.setCalciumAlginate(GTMaterialBuilder.addMaterial(2332, "calcium_alginate", GTLiteFirstDegreeMaterials::init$lambda$351));
      GTLiteMaterials.INSTANCE.setChromaticGlass(GTMaterialBuilder.addMaterial(2333, "chromatic_glass", GTLiteFirstDegreeMaterials::init$lambda$352));
      GTLiteMaterials.INSTANCE.setLanthanumHexaboride(GTMaterialBuilder.addMaterial(2334, "lanthanum_hexaboride", GTLiteFirstDegreeMaterials::init$lambda$353));
      GTLiteMaterials.INSTANCE.setFranciumCarbide(GTMaterialBuilder.addMaterial(2335, "francium_carbide", GTLiteFirstDegreeMaterials::init$lambda$354));
      GTLiteMaterials.INSTANCE.setBoronCarbide(GTMaterialBuilder.addMaterial(2336, "boron_carbide", GTLiteFirstDegreeMaterials::init$lambda$355));
      GTLiteMaterials.INSTANCE.setAstatineAzide(GTMaterialBuilder.addMaterial(2337, "astatine_azide", GTLiteFirstDegreeMaterials::init$lambda$356));
      GTLiteMaterials.INSTANCE.setHolmiumIodide(GTMaterialBuilder.addMaterial(2338, "holmium_iodide", GTLiteFirstDegreeMaterials::init$lambda$357));
      GTLiteMaterials.INSTANCE.setHeavyConductiveMixture(GTMaterialBuilder.addMaterial(2339, "heavy_conductive_mixture", GTLiteFirstDegreeMaterials::init$lambda$358));
      GTLiteMaterials.INSTANCE.setPlutoniumDioxide(GTMaterialBuilder.addMaterial(2340, "plutonium_dioxide", GTLiteFirstDegreeMaterials::init$lambda$359));
      GTLiteMaterials.INSTANCE.setMOX(GTMaterialBuilder.addMaterial(2341, "mox", GTLiteFirstDegreeMaterials::init$lambda$360));
      GTLiteMaterials.INSTANCE.setBariumManganate(GTMaterialBuilder.addMaterial(2342, "barium_manganate", GTLiteFirstDegreeMaterials::init$lambda$361));
      GTLiteMaterials.INSTANCE.setBariumDichloride(GTMaterialBuilder.addMaterial(2343, "barium_dichloride", GTLiteFirstDegreeMaterials::init$lambda$362));
      GTLiteMaterials.INSTANCE.setThalliumChloride(GTMaterialBuilder.addMaterial(2344, "thallium_chloride", GTLiteFirstDegreeMaterials::init$lambda$363));
      GTLiteMaterials.INSTANCE.setHassiumTetrachloride(GTMaterialBuilder.addMaterial(2345, "hassium_tetrachloride", GTLiteFirstDegreeMaterials::init$lambda$364));
      GTLiteMaterials.INSTANCE.setRheniumPentachloride(GTMaterialBuilder.addMaterial(2346, "rhenium_pentachloride", GTLiteFirstDegreeMaterials::init$lambda$365));
      GTLiteMaterials.INSTANCE.setHexafluorophosphoricAcid(GTMaterialBuilder.addMaterial(2347, "hexafluorophosphoric_acid", GTLiteFirstDegreeMaterials::init$lambda$367));
      GTLiteMaterials.INSTANCE.setSodiumThiocyanate(GTMaterialBuilder.addMaterial(2348, "sodium_thiocyanate", GTLiteFirstDegreeMaterials::init$lambda$368));
      GTLiteMaterials.INSTANCE.setRheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate(GTMaterialBuilder.addMaterial(2349, "rhenium_hassium_thallium_isophtaloylbisdiethylthiourea_hexafluorophosphate", GTLiteFirstDegreeMaterials::init$lambda$369));
      GTLiteMaterials.INSTANCE.setSolarGradeSilicon(GTMaterialBuilder.addMaterial(2350, "solar_grade_silicon", GTLiteFirstDegreeMaterials::init$lambda$370));
   }

   private static final Unit init$lambda$0(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.ore();
      $this$addMaterial.color(12302514).iconSet(MaterialIconSet.DULL);
      MaterialFlag[] var1 = new Object[]{Materials.Calcium, 1, Materials.Magnesium, 1, Materials.Carbon, 2, Materials.Oxygen, 6};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$1(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem(2);
      $this$addMaterial.ore();
      $this$addMaterial.color(4194504).iconSet(MaterialIconSet.GEM_VERTICAL);
      MaterialFlag[] var1 = new Object[]{Materials.Calcium, 2, Materials.Aluminium, 3, Materials.Silicon, 3, Materials.Hydrogen, 1, Materials.Oxygen, 13};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.NO_SMELTING, MaterialFlags.HIGH_SIFTER_OUTPUT, MaterialFlags.CRYSTALLIZABLE, MaterialFlags.GENERATE_LENS};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$2(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.ore();
      $this$addMaterial.color(3042766).iconSet(MaterialIconSet.DULL);
      Object[] var1 = new Object[]{Materials.Copper, 3, Materials.Carbon, 2, Materials.Oxygen, 8, Materials.Hydrogen, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$3(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.ore();
      $this$addMaterial.color(1926159).iconSet(MaterialIconSet.LAPIS);
      MaterialFlag[] var1 = new Object[]{Materials.Magnesium, 2, Materials.Silicon, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING, MaterialFlags.GENERATE_LENS, GTLiteMaterialFlags.GENERATE_BOULE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$4(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.ore();
      $this$addMaterial.color(1775383).iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Calcium, 2, Materials.Magnesium, 3, Materials.Iron, 3, Materials.Silicon, 8, Materials.Oxygen, 24};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$5(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.ore();
      $this$addMaterial.color(10985026).iconSet(MaterialIconSet.DULL);
      MaterialFlag[] var1 = new Object[]{Materials.Magnesium, 3, Materials.Silicon, 2, Materials.Oxygen, 9, Materials.Hydrogen, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$6(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.ore();
      $this$addMaterial.color(9144170);
      MaterialFlag[] var1 = new Object[]{Materials.Potassium, 1, Materials.Aluminium, 3, Materials.Silicon, 3, Materials.Hydrogen, 10, Materials.Oxygen, 12};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$7(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.ore();
      $this$addMaterial.color(3161656).iconSet(MaterialIconSet.EMERALD);
      MaterialFlag[] var1 = new Object[]{Materials.Magnesium, 5, Materials.Aluminium, 2, Materials.Silicon, 3, Materials.Hydrogen, 8, Materials.Oxygen, 18};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING, MaterialFlags.GENERATE_LENS, MaterialFlags.CRYSTALLIZABLE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$8(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.ore();
      $this$addMaterial.color(12888471).iconSet(MaterialIconSet.CERTUS);
      MaterialFlag[] var1 = new Object[]{Materials.Sodium, 1, Materials.Aluminium, 1, Materials.Silicon, 3, Materials.Oxygen, 8};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING, MaterialFlags.GENERATE_LENS, MaterialFlags.CRYSTALLIZABLE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$9(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.ore();
      $this$addMaterial.color(2583116).iconSet(MaterialIconSet.GEM_HORIZONTAL);
      MaterialFlag[] var1 = new Object[]{Materials.Calcium, 1, Materials.Fluorine, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING, MaterialFlags.GENERATE_LENS, GTLiteMaterialFlags.GENERATE_BOULE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$10(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.ore();
      $this$addMaterial.color(5855315).iconSet(MaterialIconSet.CERTUS);
      MaterialFlag[] var1 = new Object[]{Materials.Calcium, 1, Materials.Aluminium, 2, Materials.Silicon, 2, Materials.Oxygen, 8};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING, MaterialFlags.GENERATE_LENS, MaterialFlags.CRYSTALLIZABLE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$11(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.ore();
      $this$addMaterial.color(12888471).iconSet(MaterialIconSet.CERTUS);
      MaterialFlag[] var1 = new Object[]{Materials.Sodium, 1, Materials.Aluminium, 1, Materials.Silicon, 3, Materials.Oxygen, 8};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING, MaterialFlags.GENERATE_LENS, MaterialFlags.CRYSTALLIZABLE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$12(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.ore();
      $this$addMaterial.color(6058369).iconSet(MaterialIconSet.RUBY);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getAlbite(), 2, GTLiteMaterials.INSTANCE.getAnorthite(), 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING, MaterialFlags.GENERATE_LENS, MaterialFlags.CRYSTALLIZABLE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$13(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.ore();
      $this$addMaterial.color(13212775).iconSet(MaterialIconSet.LAPIS);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getAlbite(), 1, GTLiteMaterials.INSTANCE.getAnorthite(), 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING, MaterialFlags.GENERATE_LENS, MaterialFlags.CRYSTALLIZABLE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$14(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.ore();
      $this$addMaterial.color(4470596).iconSet(MaterialIconSet.DULL);
      MaterialFlag[] var1 = new Object[]{Materials.Copper, 1, Materials.Oxygen, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$15(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.ore();
      $this$addMaterial.color(10037550).iconSet(MaterialIconSet.DULL);
      MaterialFlag[] var1 = new Object[]{Materials.Copper, 2, Materials.Oxygen, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$16(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.ore();
      $this$addMaterial.color(14671839).iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Calcium, 1, Materials.Silicon, 1, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$17(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.ore();
      $this$addMaterial.color(5223384).iconSet(MaterialIconSet.QUARTZ);
      MaterialFlag[] var1 = new Object[]{Materials.Calcium, 5, Materials.Phosphorus, 3, Materials.Oxygen, 12, Materials.Fluorine, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING, MaterialFlags.HIGH_SIFTER_OUTPUT, MaterialFlags.GENERATE_LENS, GTLiteMaterialFlags.GENERATE_BOULE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$18(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.ore();
      $this$addMaterial.color(14404294).iconSet(MaterialIconSet.DULL);
      MaterialFlag[] var1 = new Object[]{Materials.Aluminium, 2, Materials.Silicon, 2, Materials.Hydrogen, 4, Materials.Oxygen, 9};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$19(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem(0, GTLiteValues.getMin(1) + GTLiteValues.getS(20));
      $this$addMaterial.ore();
      $this$addMaterial.color(6571590).iconSet(MaterialIconSet.LIGNITE);
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 3, Materials.Water, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.FLAMMABLE, MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.NO_SMELTING, MaterialFlags.NO_SMASHING, MaterialFlags.MORTAR_GRINDABLE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$20(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem(1, GTLiteValues.getMin(2) + GTLiteValues.getS(40));
      $this$addMaterial.ore();
      $this$addMaterial.color(13112320).iconSet(MaterialIconSet.QUARTZ);
      MaterialFlag[] var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.NO_SMELTING, MaterialFlags.CRYSTALLIZABLE};
      $this$addMaterial.flags(var1);
      Object[] var2 = new Object[]{Materials.SiliconDioxide, 2, Materials.Flint, 1, Materials.Pyrite, 1};
      $this$addMaterial.components(var2);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$21(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(11574681);
      MaterialFlag[] var1 = new Object[]{Materials.Iron, 2, Materials.Sulfur, 3, Materials.Oxygen, 12};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$22(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem(2);
      $this$addMaterial.ore();
      $this$addMaterial.color(4908006).iconSet(MaterialIconSet.OPAL);
      MaterialFlag[] var1 = new Object[]{Materials.Strontium, 1, Materials.Sulfur, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.CRYSTALLIZABLE, MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS, GTLiteMaterialFlags.GENERATE_BOULE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$23(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.ore();
      $this$addMaterial.color(1945555).iconSet(MaterialIconSet.SAND);
      MaterialFlag[] var1 = new Object[]{Materials.Strontium, 1, Materials.Carbon, 1, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$24(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.DULL);
      MaterialFlag[] var1 = new Object[]{Materials.Strontium, 1, Materials.Oxygen, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$25(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Strontium, 1, Materials.Sulfur, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$26(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(7914475).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Aluminium, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$27(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.ore();
      $this$addMaterial.color(14474509);
      MaterialFlag[] var1 = new Object[]{Materials.Potassium, 1, Materials.Magnesium, 3, Materials.Aluminium, 1, Materials.Silicon, 3, Materials.Oxygen, 10, Materials.Fluorine, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$28(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.ore();
      $this$addMaterial.color(6856607).iconSet(MaterialIconSet.GEM_HORIZONTAL);
      MaterialFlag[] var1 = new Object[]{Materials.Zirconium, 1, Materials.Oxygen, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.HIGH_SIFTER_OUTPUT, MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS, GTLiteMaterialFlags.GENERATE_BOULE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$29(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.ore();
      $this$addMaterial.color(15034434).iconSet(MaterialIconSet.CERTUS);
      MaterialFlag[] var1 = new Object[]{Materials.Potassium, 1, Materials.Sodium, 3, Materials.Aluminium, 4, Materials.Silicon, 4, Materials.Oxygen, 16};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS, MaterialFlags.CRYSTALLIZABLE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$30(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.ore();
      $this$addMaterial.color(4901435).iconSet(MaterialIconSet.EMERALD);
      MaterialFlag[] var1 = new Object[]{Materials.Sodium, 1, Materials.Iron, 1, Materials.Silicon, 2, Materials.Oxygen, 6};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS, MaterialFlags.CRYSTALLIZABLE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$31(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(12234947).iconSet(MaterialIconSet.ROUGH);
      Object[] var1 = new Object[]{Materials.Niobium, 2, Materials.Oxygen, 5};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$32(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(7500426).iconSet(MaterialIconSet.ROUGH);
      Object[] var1 = new Object[]{Materials.Tantalum, 2, Materials.Oxygen, 5};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$33(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(16776350).iconSet(MaterialIconSet.ROUGH);
      Object[] var1 = new Object[]{Materials.Calcium, 1, Materials.Fluorine, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$34(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(15682365).iconSet(MaterialIconSet.ROUGH);
      Object[] var1 = new Object[]{Materials.Manganese, 1, Materials.Fluorine, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$35(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(9392979);
      MaterialFlag[] var1 = new Object[]{Materials.Rubidium, 1, Materials.Caesium, 2, Materials.Chlorine, 6, Materials.Water, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$36(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(14408667).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Tin, 1, Materials.Chlorine, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$37(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(3390453).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Tin, 1, Materials.Chlorine, 4};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$38(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(12430728).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Caesium, 2, Materials.Tin, 1, Materials.Chlorine, 6};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$39(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(12421258).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Rubidium, 2, Materials.Tin, 1, Materials.Chlorine, 6};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$40(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.ore();
      $this$addMaterial.color(12578815).iconSet(MaterialIconSet.QUARTZ);
      MaterialFlag[] var1 = new Object[]{Materials.Sodium, 3, Materials.Aluminium, 1, Materials.Fluorine, 6};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS, MaterialFlags.CRYSTALLIZABLE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$41(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(12500680);
      Object[] var1 = new Object[]{Materials.Aluminium, 1, Materials.Oxygen, 3, Materials.Hydrogen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$42(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage();
      MaterialFlag[] var1 = new Object[]{Materials.Sodium, 1, Materials.Aluminium, 1, Materials.Oxygen, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$43(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.colorAverage();
      MaterialFlag[] var1 = new Object[]{Materials.SodaAsh, 1, Materials.Water, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$44(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage();
      Object[] var1 = new Object[]{Materials.Aluminium, 2, Materials.Sulfur, 3, Materials.Oxygen, 12};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$45(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Sodium, 1, GTLiteMaterials.INSTANCE.getAluminiumSulfate(), 1, Materials.SiliconDioxide, 2, Materials.Water, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.GENERATE_PLATE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$46(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(13357018).iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Molybdenum, 1, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$48$lambda$47(FluidBuilder $this$gas) {
      Intrinsics.checkNotNullParameter($this$gas, "$this$gas");
      $this$gas.translation("gregtech.fluid.generic");
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$48(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      MaterialExtKt.gas($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$48$lambda$47);
      $this$addMaterial.color(3742026);
      MaterialFlag[] var1 = new Object[]{Materials.Rhenium, 1, Materials.Oxygen, 2, Materials.RareEarth, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$49(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(15987699).iconSet(MaterialIconSet.ROUGH);
      Object[] var1 = new Object[]{Materials.Lead, 1, Materials.Chlorine, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$51$lambda$50(FluidBuilder $this$gas) {
      Intrinsics.checkNotNullParameter($this$gas, "$this$gas");
      $this$gas.translation("gregtech.fluid.generic");
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$51(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      MaterialExtKt.gas($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$51$lambda$50);
      $this$addMaterial.color(9885397);
      MaterialFlag[] var1 = new Object[]{Materials.Rhenium, 1, Materials.Oxygen, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$52(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(15129712).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Hydrogen, 1, Materials.Rhenium, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$53(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(10918256).iconSet(MaterialIconSet.METALLIC);
      MaterialFlag[] var1 = new Object[]{Materials.Nitrogen, 1, Materials.Hydrogen, 4, Materials.Rhenium, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$54(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem(2);
      $this$addMaterial.ore();
      $this$addMaterial.color(25600).iconSet(MaterialIconSet.RUBY);
      MaterialFlag[] var1 = new Object[]{Materials.Sodium, 1, Materials.Aluminium, 1, Materials.Silicon, 2, Materials.Oxygen, 6};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.CRYSTALLIZABLE, MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS, MaterialFlags.CRYSTALLIZABLE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$55(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem(2);
      $this$addMaterial.ore();
      $this$addMaterial.color(13127760).iconSet(MaterialIconSet.EMERALD);
      MaterialFlag[] var1 = new Object[]{Materials.Calcium, 1, Materials.Magnesium, 5, Materials.Oxygen, 24, Materials.Hydrogen, 2, Materials.Silicon, 8};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.HIGH_SIFTER_OUTPUT, MaterialFlags.CRYSTALLIZABLE, MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS, MaterialFlags.CRYSTALLIZABLE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$56(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem(3);
      $this$addMaterial.ore(2, 3);
      $this$addMaterial.color(9640996).iconSet(MaterialIconSet.DIAMOND);
      MaterialFlag[] var1 = new Object[]{Materials.Iron, 1, Materials.Chrome, 2, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS, GTLiteMaterialFlags.GENERATE_BOULE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$57(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(4662272);
      Object[] var1 = new Object[]{Materials.Manganese, 1, Materials.Oxygen, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$58(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(16775936).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Lead, 1, Materials.Chrome, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$59(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(16777215).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Lead, 1, Materials.Nitrogen, 2, Materials.Oxygen, 6};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$60(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(1443327).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Cobalt, 1, Materials.Aluminium, 2, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$61(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.ore();
      $this$addMaterial.color(15455058).iconSet(MaterialIconSet.EMERALD);
      MaterialFlag[] var1 = new Object[]{Materials.Arsenic, 2, Materials.Sulfur, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS, MaterialFlags.CRYSTALLIZABLE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$62(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Sodium, 1, Materials.Chlorine, 1, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$63(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(Materials.Salt.getMaterialRGB()).iconSet(MaterialIconSet.ROUGH);
      Object[] var1 = new Object[]{Materials.Sodium, 1, Materials.Chlorine, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$64(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(7834966).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Sodium, 1, Materials.Chlorine, 1, Materials.Oxygen, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$65(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(13095680).iconSet(MaterialIconSet.DULL);
      MaterialFlag[] var1 = new Object[]{Materials.Tungsten, 1, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$66(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.MAGNETIC);
      MaterialFlag[] var1 = new Object[]{Materials.Strontium, 1, Materials.Iron, 12, Materials.Oxygen, 19};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.GENERATE_ROD, MaterialFlags.GENERATE_RING};
      $this$addMaterial.flags(var1);
      MaterialExtKt.blastProp($this$addMaterial, 3000, GasTier.MID, GTValues.VA[4], GTLiteValues.getS(40), GTValues.VA[2], GTLiteValues.getS(10));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$67(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage();
      Object[] var1 = new Object[]{Materials.Titanium, 1, Materials.Nitrogen, 4, Materials.Oxygen, 12};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$69$lambda$68(MaterialToolProperty.Builder $this$toolProp) {
      Intrinsics.checkNotNullParameter($this$toolProp, "$this$toolProp");
      $this$toolProp.magnetic();
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$69(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(16675241).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Lithium, 2, Materials.Titanium, 1, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.EXT2_METAL;
      var1 = new MaterialFlag[]{GTLiteMaterialFlags.NO_ALLOY_BLAST_RECIPES, MaterialFlags.GENERATE_DOUBLE_PLATE, MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE, MaterialFlags.GENERATE_GEAR, MaterialFlags.GENERATE_RING, MaterialFlags.GENERATE_SMALL_GEAR, MaterialFlags.GENERATE_SPRING_SMALL};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 3100, GasTier.MID, GTValues.VA[4], GTLiteValues.getS(16), GTValues.VA[2], GTLiteValues.getS(8));
      MaterialExtKt.toolProp($this$addMaterial, 8.5F, 7.0F, 2304, 4, GTLiteFirstDegreeMaterials::init$lambda$69$lambda$68);
      MaterialExtKt.rotorProp($this$addMaterial, 8.5F, 4.0F, 3200);
      MaterialExtKt.fluidPipeProp$default($this$addMaterial, 2830, 200, true, true, false, false, 48, (Object)null);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$70(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(8532266).iconSet(MaterialIconSet.METALLIC);
      MaterialFlag[] var1 = new Object[]{Materials.Palladium, 1, Materials.Nitrogen, 2, Materials.Oxygen, 6};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$71(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(6896685).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Palladium, 1, Materials.AceticAcid, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$72(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(GTLiteMaterialIconSet.NANOPARTICLES);
      MaterialFlag[] var1 = new Object[]{Materials.Palladium, 1, Materials.Rutile, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$73(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(10335929).iconSet(MaterialIconSet.DULL);
      Object[] var1 = new Object[]{Materials.Lithium, 2, Materials.Oxygen, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$74(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(13759478).iconSet(MaterialIconSet.ROUGH);
      Object[] var1 = new Object[]{Materials.Lithium, 2, Materials.Carbon, 1, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$75(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(4342494);
      MaterialFlag[] var1 = new Object[]{Materials.Copper, 1, Materials.Sulfur, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$76(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(13027774).iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Sodium, 2, Materials.Tellurium, 1, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$77(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(14736856).iconSet(MaterialIconSet.METALLIC);
      MaterialFlag[] var1 = new Object[]{Materials.Selenium, 1, Materials.Oxygen, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$78(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(14933432).iconSet(MaterialIconSet.METALLIC);
      MaterialFlag[] var1 = new Object[]{Materials.Tellurium, 1, Materials.Oxygen, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$79(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(14737539).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Hydrogen, 2, Materials.Selenium, 1, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$80(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(9868881);
      Object[] var1 = new Object[]{Materials.Aluminium, 2, Materials.Selenium, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$81(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gas();
      $this$addMaterial.color(4388180);
      Object[] var1 = new Object[]{Materials.Hydrogen, 2, Materials.Selenium, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$82(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(16717684).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Cadmium, 1, Materials.Bromine, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$83(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(6245426).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Magnesium, 1, Materials.Bromine, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$84(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(Materials.Magnesium.getMaterialRGB()).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Magnesium, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$86$lambda$85(FluidBuilder $this$liquid) {
      Intrinsics.checkNotNullParameter($this$liquid, "$this$liquid");
      FluidAttribute[] var1 = new FluidAttribute[]{FluidAttributes.ACID};
      $this$liquid.attributes(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$86(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      MaterialExtKt.liquid($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$86$lambda$85);
      $this$addMaterial.color(9245202);
      Object[] var1 = new Object[]{Materials.Hydrogen, 1, Materials.Bromine, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$87(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(6030207);
      Object[] var1 = new Object[]{Materials.Carbon, 2, Materials.Hydrogen, 6, Materials.Cadmium, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$88(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.liquid();
      $this$addMaterial.color(9973812).iconSet(GTLiteMaterialIconSet.NANOPARTICLES);
      MaterialFlag[] var1 = new Object[]{Materials.Cadmium, 1, Materials.Selenium, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$89(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.ore();
      $this$addMaterial.color(10401609).iconSet(MaterialIconSet.QUARTZ);
      MaterialFlag[] var1 = new Object[]{Materials.SiliconDioxide, 5, Materials.Iron, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS, MaterialFlags.CRYSTALLIZABLE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$90(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(11318460).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Zirconium, 5, Materials.Barium, 2, Materials.Lanthanum, 1, Materials.Aluminium, 1, Materials.Sodium, 2, Materials.Fluorine, 6};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.NO_WORKING, MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$91(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.color(5264452).iconSet(MaterialIconSet.BRIGHT);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getZBLANGlass(), 1, Materials.Erbium, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.NO_WORKING, MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.GENERATE_PLATE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$92(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.color(12961933).iconSet(MaterialIconSet.BRIGHT);
      MaterialFlag[] var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.NO_WORKING, MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.GENERATE_PLATE};
      $this$addMaterial.flags(var1);
      Object[] var2 = new Object[]{GTLiteMaterials.INSTANCE.getZBLANGlass(), 1, Materials.Praseodymium, 1};
      $this$addMaterial.components(var2);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$93(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gas();
      $this$addMaterial.color(12514554);
      MaterialFlag[] var1 = new Object[]{Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$94(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.color(16768994).iconSet(MaterialIconSet.DIAMOND);
      MaterialFlag[] var1 = new Object[]{Materials.Zirconium, 1, Materials.Oxygen, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS, GTLiteMaterialFlags.GENERATE_BOULE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$95(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(952627).iconSet(MaterialIconSet.DULL);
      Object[] var1 = new Object[]{Materials.Bismuth, 2, Materials.Tellurium, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$96(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.color(16750591).iconSet(GTLiteMaterialIconSet.MAGNETO);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getBismuthTelluride(), 4, GTLiteMaterials.INSTANCE.getPrasiolite(), 3, GTLiteMaterials.INSTANCE.getCubicZirconia(), 1, Materials.SteelMagnetic, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.NO_SMELTING, MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS, GTLiteMaterialFlags.GENERATE_BOULE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$97(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(6256503).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Lanthanum, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$98(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(1086335).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Cerium, 1, Materials.Oxygen, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$99(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(13684944).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Praseodymium, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$100(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(8816262);
      Object[] var1 = new Object[]{Materials.Neodymium, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$101(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(16777181);
      Object[] var1 = new Object[]{Materials.Samarium, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$102(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(2140842).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Europium, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$103(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(15658751).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Gadolinium, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$104(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(10642594).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Terbium, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$105(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(13792210).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Dysprosium, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$106(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(11501354).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Holmium, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$107(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(14711346).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Erbium, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$108(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(3907211);
      Object[] var1 = new Object[]{Materials.Thulium, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$109(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(11119017);
      Object[] var1 = new Object[]{Materials.Ytterbium, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$110(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(1162239).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Lutetium, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$111(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(4429391).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Scandium, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$112(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(7885902).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Yttrium, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$113(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(1804328).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Promethium, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$114(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(10281947);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getLanthanumOxide(), 1, GTLiteMaterials.INSTANCE.getPraseodymiumOxide(), 1, GTLiteMaterials.INSTANCE.getNeodymiumOxide(), 1, GTLiteMaterials.INSTANCE.getCeriumOxide(), 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$115(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(16777113);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getScandiumOxide(), 1, GTLiteMaterials.INSTANCE.getEuropiumOxide(), 1, GTLiteMaterials.INSTANCE.getGadoliniumOxide(), 1, GTLiteMaterials.INSTANCE.getSamariumOxide(), 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$116(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(10092441);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getYttriumOxide(), 1, GTLiteMaterials.INSTANCE.getTerbiumOxide(), 1, GTLiteMaterials.INSTANCE.getDysprosiumOxide(), 1, GTLiteMaterials.INSTANCE.getHolmiumOxide(), 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$117(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(16757759);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getErbiumOxide(), 1, GTLiteMaterials.INSTANCE.getThuliumOxide(), 1, GTLiteMaterials.INSTANCE.getYtterbiumOxide(), 1, GTLiteMaterials.INSTANCE.getLutetiumOxide(), 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$118(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(6578990).iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Iridium, 1, Materials.Osmium, 1, Materials.Rhodium, 1, Materials.Ruthenium, 1, Materials.RareEarth, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$119(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(16777126);
      MaterialFlag[] var1 = new Object[]{Materials.Gold, 1, Materials.Platinum, 1, Materials.Palladium, 1, Materials.HydrochloricAcid, 6};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$120(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(16777160);
      MaterialFlag[] var1 = new Object[]{Materials.Hydrogen, 2, Materials.Platinum, 1, Materials.Palladium, 1, Materials.Chlorine, 6};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$121(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(16707778);
      MaterialFlag[] var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      Object[] var2 = new Object[]{Materials.Nitrogen, 2, Materials.Hydrogen, 8, Materials.Platinum, 1, Materials.Chlorine, 6};
      $this$addMaterial.components(var2);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$122(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(8421504);
      MaterialFlag[] var1 = new Object[]{Materials.Nitrogen, 2, Materials.Hydrogen, 8, Materials.Palladium, 1, Materials.Chlorine, 6};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$123(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(8676996).iconSet(MaterialIconSet.ROUGH);
      Object[] var1 = new Object[]{Materials.Sodium, 1, Materials.Nitrogen, 1, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$125$lambda$124(FluidBuilder $this$liquid) {
      Intrinsics.checkNotNullParameter($this$liquid, "$this$liquid");
      $this$liquid.attribute(FluidAttributes.ACID);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$125(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      MaterialExtKt.liquid($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$125$lambda$124);
      $this$addMaterial.color(16708817);
      Object[] var1 = new Object[]{Materials.Hydrogen, 2, Materials.Platinum, 1, Materials.Chlorine, 6};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$126(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(7675930);
      Object[] var1 = new Object[]{Materials.Carbon, 1, Materials.Chlorine, 4};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$127(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(15531904).iconSet(MaterialIconSet.ROUGH);
      Object[] var1 = new Object[]{Materials.Sodium, 2, Materials.Oxygen, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$128(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(6315116).iconSet(MaterialIconSet.METALLIC);
      MaterialFlag[] var1 = new Object[]{Materials.Ruthenium, 1, Materials.Chlorine, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$129(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(14236950).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Rhodium, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$130(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(7738384);
      Object[] var1 = new Object[]{Materials.Sulfur, 1, Materials.Chlorine, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$131(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(2689034).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Osmium, 1, Materials.Chlorine, 4};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$132(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.color(5556055);
      MaterialFlag[] var1 = new Object[]{Materials.Beryllium, 1, Materials.Oxygen, 1};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.EXT_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.GENERATE_DOUBLE_PLATE, MaterialFlags.GENERATE_RING};
      $this$addMaterial.flags(var10001, var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$133(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(13828095);
      Object[] var1 = new Object[]{Materials.Hydrogen, 2, Materials.Oxygen, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$134(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(7829367).iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Graphene, 1, Materials.Oxygen, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$135(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Yttrium, 1, Materials.Nitrogen, 3, Materials.Oxygen, 9};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$136(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Barium, 1, Materials.Nitrogen, 2, Materials.Oxygen, 6};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$137(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.DULL);
      Object[] var1 = new Object[]{Materials.Copper, 1, Materials.Nitrogen, 2, Materials.Oxygen, 6};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$138(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Yttrium, 1, Materials.Barium, 2, Materials.Copper, 3, Materials.Oxygen, 6};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$139(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(13631487).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Germanium, 2, Materials.Antimony, 2, Materials.Tellurium, 5};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.NO_WORKING, MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING, MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_LENS, MaterialFlags.GENERATE_FINE_WIRE};
      $this$addMaterial.flags(var1);
      MaterialExtKt.blastProp($this$addMaterial, 873, GasTier.MID);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$140(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(6710886);
      MaterialFlag[] var1 = new Object[]{Materials.Germanium, 1, Materials.Oxygen, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$141(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(11307868).iconSet(GTLiteMaterialIconSet.ROASTED);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getGermaniumDioxide(), 1, Materials.RareEarth, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$142(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(12823695).iconSet(MaterialIconSet.METALLIC);
      MaterialFlag[] var1 = new Object[]{Materials.Zinc, 2, GTLiteMaterials.INSTANCE.getRoastedSphalerite(), 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$143(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(12105912).iconSet(MaterialIconSet.FINE);
      MaterialFlag[] var1 = new Object[]{Materials.Zinc, 1, GTLiteMaterials.INSTANCE.getGermaniumDioxide(), 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$144(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(11307868).iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Gallium, 1, Materials.Zinc, 1, Materials.Sulfur, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$145(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(4726955);
      MaterialFlag[] var1 = new Object[]{Materials.SulfuricAcid, 1, GTLiteMaterials.INSTANCE.getHydrogenPeroxide(), 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$146(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.color(4151967).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Aluminium, 1, Materials.Carbon, 9, Materials.Hydrogen, 7, Materials.Nitrogen, 1, Materials.Oxygen, 1};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.STD_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.GENERATE_FOIL};
      $this$addMaterial.flags(var10001, var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$148$lambda$147(FluidBuilder $this$liquid) {
      Intrinsics.checkNotNullParameter($this$liquid, "$this$liquid");
      $this$liquid.attribute(FluidAttributes.ACID);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$148(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      MaterialExtKt.liquid($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$148$lambda$147);
      $this$addMaterial.color(14402485);
      MaterialFlag[] var1 = new Object[]{Materials.Hydrogen, 2, Materials.Selenium, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$149(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Copper, 1, Materials.Gallium, 1, Materials.Indium, 1, Materials.Selenium, 2};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.STD_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 6000, GasTier.MID, GTValues.VA[4], GTLiteValues.getS(30), GTValues.VA[2], GTLiteValues.getS(10));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$150(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(16777197).iconSet(MaterialIconSet.DULL);
      Object[] var1 = new Object[]{Materials.Barium, 1, Materials.Oxygen, 2, Materials.Hydrogen, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$151(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(10092441).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Barium, 1, Materials.Titanium, 1, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.EXT_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, GTLiteMaterialFlags.NO_ALLOY_BLAST_RECIPES, MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_BOLT_SCREW};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 3600, GasTier.LOW, GTValues.VA[5], GTLiteValues.getS(18), GTValues.VA[3], GTLiteValues.getS(8));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$152(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(11785859).iconSet(MaterialIconSet.MAGNETIC);
      MaterialFlag[] var1 = new Object[]{Materials.Samarium, 1, Materials.Cobalt, 5};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.GENERATE_ROD, MaterialFlags.GENERATE_LONG_ROD, MaterialFlags.GENERATE_RING};
      $this$addMaterial.flags(var1);
      MaterialExtKt.blastProp($this$addMaterial, 5000, GasTier.HIGH, GTValues.VA[5], GTLiteValues.getS(45), GTValues.VA[3], GTLiteValues.getS(20));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$154$lambda$153(FluidBuilder $this$liquid) {
      Intrinsics.checkNotNullParameter($this$liquid, "$this$liquid");
      $this$liquid.temperature(633);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$154(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      MaterialExtKt.liquid($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$154$lambda$153);
      $this$addMaterial.color(16422985);
      Object[] var1 = new Object[]{Materials.Potassium, 1, Materials.Oxygen, 1, Materials.Hydrogen, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$155(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(4174776).iconSet(MaterialIconSet.ROUGH);
      Object[] var1 = new Object[]{Materials.Copper, 1, Materials.Chlorine, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$156(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(1783832).iconSet(MaterialIconSet.DULL);
      Object[] var1 = new Object[]{Materials.Sodium, 1, Materials.Carbon, 1, Materials.Nitrogen, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$157(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(7874600).iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Potassium, 1, Materials.Bromine, 1, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$158(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.color(15526621).iconSet(MaterialIconSet.FLINT);
      MaterialFlag[] var1 = new Object[]{Materials.Phosphorus, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$159(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.color(7799822).iconSet(MaterialIconSet.FLINT);
      MaterialFlag[] var1 = new Object[]{Materials.Phosphorus, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$160(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.color(8388863).iconSet(MaterialIconSet.FLINT);
      MaterialFlag[] var1 = new Object[]{Materials.Phosphorus, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$161(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.color(3556687).iconSet(MaterialIconSet.FLINT);
      MaterialFlag[] var1 = new Object[]{Materials.Phosphorus, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$162(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.color(10216420).iconSet(MaterialIconSet.FLINT);
      MaterialFlag[] var1 = new Object[]{Materials.Phosphorus, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$163(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(14211163);
      Object[] var1 = new Object[]{Materials.Phosphorus, 1, Materials.Chlorine, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$164(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(4587538).iconSet(MaterialIconSet.DULL);
      Object[] var1 = new Object[]{Materials.Sodium, 1, Materials.Fluorine, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$165(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(5244990).iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Sodium, 1, Materials.Carbon, 2, Materials.Hydrogen, 4, Materials.Oxygen, 1, Materials.Fluorine, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$166(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(16574884).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Technetium, 2, Materials.Oxygen, 7};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$167(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(12597189).iconSet(MaterialIconSet.METALLIC);
      MaterialFlag[] var1 = new Object[]{Materials.Trinium, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$169$lambda$168(FluidBuilder $this$liquid) {
      Intrinsics.checkNotNullParameter($this$liquid, "$this$liquid");
      FluidAttribute[] var1 = new FluidAttribute[]{FluidAttributes.ACID};
      $this$liquid.attributes(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$169(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      MaterialExtKt.liquid($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$169$lambda$168);
      $this$addMaterial.color(13382400);
      MaterialFlag[] var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      Object[] var2 = new Object[]{Materials.Hydrogen, 1, Materials.Technetium, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var2);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$170(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(10854103).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Ammonia, 1, Materials.NitricAcid, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$171(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(10053222).iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Nitrogen, 1, Materials.Hydrogen, 4, Materials.Technetium, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$172(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Technetium, 1, Materials.Oxygen, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$173(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(2829936).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Indium, 1, Materials.Phosphorus, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$174(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(11246271).iconSet(MaterialIconSet.DULL);
      Object[] var1 = new Object[]{Materials.Gallium, 1, Materials.Oxygen, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$175(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(16382457).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Calcium, 1, Materials.Sulfur, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$176(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(16460296);
      MaterialFlag[] var1 = new Object[]{Materials.CoalTar, 1, Materials.Oxygen, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$177(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(16117570).iconSet(MaterialIconSet.FINE);
      Object[] var1 = new Object[]{Materials.Bismuth, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$178(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(14188760);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getBismuthTrioxide(), 1, GTLiteMaterials.INSTANCE.getStrontianite(), 2, Materials.Calcite, 1, GTLiteMaterials.INSTANCE.getTenorite(), 2};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.STD_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING, GTLiteMaterialFlags.NO_ALLOY_BLAST_RECIPES, MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 7000, GasTier.HIGHER, GTValues.VA[8], GTLiteValues.getS(43), GTValues.VA[5], GTLiteValues.getS(21));
      MaterialExtKt.cableProp$default($this$addMaterial, GTValues.V[8], 4, 3, false, 8, (Object)null);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$179(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.iconSet(GTLiteMaterialIconSet.BEDROCKIUM);
      Collection var10001 = (Collection)Materials.EXT2_METAL;
      MaterialFlag[] var1 = new MaterialFlag[]{MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_DOUBLE_PLATE, MaterialFlags.GENERATE_DENSE, MaterialFlags.GENERATE_FRAME, MaterialFlags.GENERATE_GEAR, MaterialFlags.GENERATE_SMALL_GEAR, MaterialFlags.GENERATE_FINE_WIRE, MaterialFlags.GENERATE_SPRING, MaterialFlags.GENERATE_SPRING_SMALL};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 9900, GasTier.HIGHER, GTValues.VA[7], GTLiteValues.getS(50), GTValues.VA[6], GTLiteValues.getS(25));
      MaterialExtKt.cableProp$default($this$addMaterial, GTValues.V[9], 2, 16, false, 8, (Object)null);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$180(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(13122620).iconSet(MaterialIconSet.ROUGH);
      Object[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getAdamantium(), 3, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$181(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(16741948);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getAdamantium(), 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$182(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(6599935).iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getVibranium(), 1, Materials.RareEarth, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$183(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(16742450);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getVibranium(), 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$184(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(9866380).iconSet(MaterialIconSet.METALLIC);
      MaterialFlag[] var1 = new Object[]{Materials.Iron, 2, Materials.Trinium, 1, Materials.Indium, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$185(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.DULL);
      MaterialFlag[] var1 = new Object[]{Materials.Sodium, 7, Materials.Potassium, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$186(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Lead, 3, Materials.Bismuth, 7};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$187(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.ROUGH);
      Object[] var1 = new Object[]{Materials.Lithium, 1, Materials.Fluorine, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$188(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.DULL);
      Object[] var1 = new Object[]{Materials.Potassium, 1, Materials.Fluorine, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$189(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.METALLIC);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getLithiumFluoride(), 1, GTLiteMaterials.INSTANCE.getSodiumFluoride(), 1, GTLiteMaterials.INSTANCE.getPotassiumFluoride(), 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$190(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Beryllium, 1, Materials.Fluorine, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$191(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getLithiumFluoride(), 1, GTLiteMaterials.INSTANCE.getBerylliumDifluoride(), 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$193$lambda$192(FluidBuilder $this$plasma) {
      Intrinsics.checkNotNullParameter($this$plasma, "$this$plasma");
      $this$plasma.temperature(10550);
      $this$plasma.translation("gregtech.fluid.generic");
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$193(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      MaterialExtKt.plasma($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$193$lambda$192);
      $this$addMaterial.colorAverage();
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getMetastableFlerovium(), 1, Materials.Ytterbium, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$194(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(11873813).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Rubidium, 2, Materials.Titanium, 1, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.EXT_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, GTLiteMaterialFlags.NO_ALLOY_BLAST_RECIPES, MaterialFlags.GENERATE_BOLT_SCREW, MaterialFlags.GENERATE_FOIL};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 4100, GasTier.LOW, GTValues.VA[6], GTLiteValues.getS(20), GTValues.VA[4], GTLiteValues.getS(16));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$195(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(2258106).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Sodium, 2, Materials.Titanium, 1, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$196(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Rubidium, 1, Materials.Chlorine, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$197(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(2922236).iconSet(MaterialIconSet.BRIGHT);
      Object[] var1 = new Object[]{Materials.Sodium, 2, Materials.Oxygen, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$198(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(12965412);
      MaterialFlag[] var1 = new Object[]{Materials.SodiumHydroxide, 1, Materials.Ethenone, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$199(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Caesium, 1, Materials.Bromine, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$200(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Francium, 1, Materials.Bromine, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$201(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Francium, 1, Materials.Caesium, 1, Materials.Cadmium, 2, Materials.Bromine, 6};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.STD_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, GTLiteMaterialFlags.NO_ALLOY_BLAST_RECIPES, MaterialFlags.GENERATE_FOIL};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 7100, GasTier.HIGHER, GTValues.VA[9], GTLiteValues.getS(20), GTValues.VA[7], GTLiteValues.getS(15));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$202(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(5618644).iconSet(MaterialIconSet.BRIGHT);
      MaterialFlag[] var1 = new Object[]{Materials.Sodium, 2, Materials.Seaborgium, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.STD_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, GTLiteMaterialFlags.NO_ALLOY_BLAST_RECIPES, MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 9800, GasTier.HIGHER, GTValues.VA[8], GTLiteValues.getS(24), GTValues.VA[7], GTLiteValues.getS(12));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$203(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Lead, 1, Materials.Scandium, 1, Materials.Tantalum, 1, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$204(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(10232364).iconSet(MaterialIconSet.METALLIC);
      MaterialFlag[] var1 = new Object[]{Materials.Thallium, 2, Materials.Sulfur, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$205(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(6723840).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Thallium, 1, Materials.Barium, 2, Materials.Calcium, 2, Materials.Copper, 3, Materials.Oxygen, 10};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.STD_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, GTLiteMaterialFlags.NO_ALLOY_BLAST_RECIPES, MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 7000, GasTier.HIGHER, GTValues.VA[8], GTLiteValues.getS(43), GTValues.VA[5], GTLiteValues.getS(21));
      MaterialExtKt.cableProp$default($this$addMaterial, GTValues.V[9], 2, 1, false, 8, (Object)null);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$206(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(8861827).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Potassium, 2, Materials.Manganese, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$207(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(8854914).iconSet(MaterialIconSet.DULL);
      Object[] var1 = new Object[]{Materials.Potassium, 1, Materials.Manganese, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$208(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(9085051).iconSet(MaterialIconSet.METALLIC);
      MaterialFlag[] var1 = new Object[]{Materials.Lanthanum, 1, Materials.Gallium, 1, Materials.Manganese, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.STD_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, GTLiteMaterialFlags.NO_ALLOY_BLAST_RECIPES, MaterialFlags.GENERATE_FOIL};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 8000, GasTier.HIGH, GTValues.VA[8], GTLiteValues.getS(45), GTValues.VA[5], GTLiteValues.getS(22) + GTLiteValues.getT(10));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$209(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(16711782).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Barium, 1, Materials.Strontium, 1, Materials.Titanium, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.EXT_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, GTLiteMaterialFlags.NO_ALLOY_BLAST_RECIPES, MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE, MaterialFlags.GENERATE_BOLT_SCREW};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 7200, GasTier.HIGH, GTValues.VA[7], GTLiteValues.getS(36), GTValues.VA[5], GTLiteValues.getS(18));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$210(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.MAGNETIC);
      MaterialFlag[] var1 = new Object[]{Materials.Lutetium, 1, Materials.Manganese, 3, Materials.Germanium, 6};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.EXT_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.GENERATE_LONG_ROD, MaterialFlags.GENERATE_RING, MaterialFlags.GENERATE_SPRING_SMALL};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 7000, GasTier.HIGHER, GTValues.VA[6], GTLiteValues.getMin(1), GTValues.VA[4], GTLiteValues.getS(30));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$211(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(16448250).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Hydrogen, 3, Materials.Boron, 1, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$212(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(15334080).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Boron, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$213(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gas();
      $this$addMaterial.color(16445841);
      Object[] var1 = new Object[]{Materials.Boron, 1, Materials.Fluorine, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$214(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.color(10203099).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Lithium, 1, Materials.Hydrogen, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$215(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(9501430).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Lithium, 1, Materials.Boron, 1, Materials.Fluorine, 4};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$216(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gas();
      $this$addMaterial.color(212763);
      Object[] var1 = new Object[]{Materials.Boron, 1, Materials.Chlorine, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$217(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.color(6974066).iconSet(MaterialIconSet.GEM_VERTICAL);
      MaterialFlag[] var1 = new Object[]{Materials.Boron, 1, Materials.Nitrogen, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, GTLiteMaterialFlags.DISABLE_CRYSTALLIZATION, MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$218(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.color(5526898).iconSet(MaterialIconSet.DIAMOND);
      MaterialFlag[] var1 = new Object[]{Materials.Boron, 1, Materials.Nitrogen, 1};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.EXT_METAL;
      var1 = new MaterialFlag[]{GTLiteMaterialFlags.DISABLE_CRYSTALLIZATION, MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.FLAMMABLE, MaterialFlags.EXPLOSIVE, MaterialFlags.GENERATE_GEAR, MaterialFlags.GENERATE_LONG_ROD, MaterialFlags.GENERATE_LENS};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.toolProp($this$addMaterial, 14.0F, 9.0F, 12400, 15);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$219(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.color(9540549).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Boron, 1, Materials.Nitrogen, 1};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.STD_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.NO_SMASHING, MaterialFlags.NO_SMELTING, MaterialFlags.GENERATE_FOIL};
      $this$addMaterial.flags(var10001, var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$220(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.color(5319282).iconSet(MaterialIconSet.GEM_HORIZONTAL);
      MaterialFlag[] var1 = new Object[]{Materials.Boron, 1, Materials.Carbon, 1, Materials.Nitrogen, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, GTLiteMaterialFlags.DISABLE_CRYSTALLIZATION, MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$221(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.color(7683494).iconSet(MaterialIconSet.DIAMOND);
      MaterialFlag[] var1 = new Object[]{Materials.Boron, 1, Materials.Carbon, 2, Materials.Nitrogen, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, GTLiteMaterialFlags.DISABLE_CRYSTALLIZATION, MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$222(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(8420208).iconSet(MaterialIconSet.DULL);
      Object[] var1 = new Object[]{Materials.Calcium, 1, Materials.Carbon, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$223(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(6260580).iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Calcium, 1, Materials.Hydrogen, 2, Materials.Oxygen, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$224(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(9375806).iconSet(MaterialIconSet.METALLIC);
      MaterialFlag[] var1 = new Object[]{Materials.Chrome, 1, Materials.Germanium, 1, Materials.Tellurium, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING, MaterialFlags.GENERATE_ROD, MaterialFlags.GENERATE_LONG_ROD};
      $this$addMaterial.flags(var1);
      MaterialExtKt.blastProp($this$addMaterial, 8900, GasTier.HIGHER, GTValues.VA[6], GTLiteValues.getMin(1) + GTLiteValues.getS(30), GTValues.VA[5], GTLiteValues.getS(20));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$225(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.color(9375806).iconSet(MaterialIconSet.MAGNETIC);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getChromiumGermaniumTelluride(), 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.GENERATE_ROD, MaterialFlags.GENERATE_LONG_ROD, MaterialFlags.IS_MAGNETIC, MaterialFlags.GENERATE_SPRING_SMALL};
      $this$addMaterial.flags(var1);
      $this$addMaterial.ingotSmeltInto(GTLiteMaterials.INSTANCE.getChromiumGermaniumTelluride());
      $this$addMaterial.arcSmeltInto(GTLiteMaterials.INSTANCE.getChromiumGermaniumTelluride());
      $this$addMaterial.macerateInto(GTLiteMaterials.INSTANCE.getChromiumGermaniumTelluride());
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$226(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(7255295).iconSet(MaterialIconSet.ROUGH);
      Object[] var1 = new Object[]{Materials.Gallium, 1, Materials.Chlorine, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$227(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(14994943).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Gallium, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$228(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.color(16774232).iconSet(MaterialIconSet.SHINY);
      $this$addMaterial.flags((Collection)Materials.STD_METAL, new MaterialFlag[0]);
      Object[] var1 = new Object[]{Materials.Gallium, 1, Materials.Nitrogen, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$229(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(7914475).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Aluminium, 1, Materials.Chlorine, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$230(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Niobium, 1, Materials.Chlorine, 5};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$231(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(13793024).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Lithium, 1, Materials.Niobium, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.STD_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, GTLiteMaterialFlags.NO_ALLOY_BLAST_RECIPES, MaterialFlags.GENERATE_LENS, MaterialFlags.GENERATE_FOIL};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 3226, GasTier.HIGH, GTValues.VA[7], GTLiteValues.getS(40), GTValues.VA[5], GTLiteValues.getS(10));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$232(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(15792277).iconSet(MaterialIconSet.ROUGH);
      Object[] var1 = new Object[]{Materials.Manganese, 1, Materials.Sulfur, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$233(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(16055216).iconSet(MaterialIconSet.DULL);
      Object[] var1 = new Object[]{Materials.Potassium, 2, Materials.Sulfur, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$234(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(5952863).iconSet(MaterialIconSet.DULL);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getYttriumOxide(), 1, GTLiteMaterials.INSTANCE.getNeodymiumOxide(), 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$235(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(3847082).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Aluminium, 1, Materials.Nitrogen, 3, Materials.Oxygen, 9};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$236(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(4227148);
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 2, Materials.Hydrogen, 8, Materials.Chlorine, 5};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$237(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(7097793);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getAlumina(), 1, Materials.Carbon, 25, Materials.Hydrogen, 56, Materials.Chlorine, 2, Materials.Nitrogen, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$238(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.color(14261732).iconSet(MaterialIconSet.GEM_VERTICAL);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getYttriumOxide(), 2, GTLiteMaterials.INSTANCE.getNeodymiumOxide(), 1, GTLiteMaterials.INSTANCE.getAlumina(), 5};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.STD_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.CRYSTALLIZABLE, MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING, MaterialFlags.GENERATE_LENS};
      $this$addMaterial.flags(var10001, var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$240$lambda$239(FluidBuilder $this$liquid) {
      Intrinsics.checkNotNullParameter($this$liquid, "$this$liquid");
      $this$liquid.temperature(1800);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$240(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      MaterialExtKt.liquid($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$240$lambda$239);
      $this$addMaterial.colorAverage();
      MaterialFlag[] var1 = new Object[]{Materials.Scandium, 1, Materials.Titanium, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$242$lambda$241(FluidBuilder $this$liquid) {
      Intrinsics.checkNotNullParameter($this$liquid, "$this$liquid");
      $this$liquid.temperature(1200);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$242(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      MaterialExtKt.liquid($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$242$lambda$241);
      $this$addMaterial.colorAverage();
      MaterialFlag[] var1 = new Object[]{Materials.Radium, 1, Materials.Radon, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$243(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Polonium, 1, Materials.Nitrogen, 4, Materials.Oxygen, 12};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$244(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.DULL);
      MaterialFlag[] var1 = new Object[]{Materials.Sodium, 2, Materials.Polonium, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$245(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Polonium, 1, Materials.Oxygen, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$246(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(14671896);
      MaterialFlag[] var1 = new Object[]{Materials.Uraninite, 1, Materials.Chlorine, 2, Materials.Water, 1, Materials.RareEarth, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$247(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.colorAverage();
      MaterialFlag[] var1 = new Object[]{Materials.Uraninite, 1, Materials.Nitrogen, 2, Materials.Oxygen, 7, Materials.Hydrogen, 2, Materials.RareEarth, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$248(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Radium, 1, Materials.Sulfur, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$249(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.DULL);
      Object[] var1 = new Object[]{Materials.Lead, 1, Materials.Sulfur, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$250(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.METALLIC);
      MaterialFlag[] var1 = new Object[]{Materials.Thorium, 1, Materials.Nitrogen, 4, Materials.Oxygen, 12};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$251(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Radium, 1, Materials.Chlorine, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$252(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Thorium, 1, Materials.Oxygen, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$253(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Uraninite, 1, Materials.Nitrogen, 2, Materials.Oxygen, 6};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$254(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.DULL);
      Object[] var1 = new Object[]{Materials.Barium, 1, Materials.Oxygen, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$255(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(7536793).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.SodaAsh, 6, Materials.SiliconDioxide, 3, GTLiteMaterials.INSTANCE.getBariumOxide(), 2, Materials.Garnierite, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.NO_SMASHING, MaterialFlags.NO_WORKING, MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING, MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE, MaterialFlags.GENERATE_LENS};
      $this$addMaterial.flags(var1);
      MaterialExtKt.blastProp($this$addMaterial, 1163, GasTier.MID, GTValues.VA[5], GTLiteValues.getS(7) + GTLiteValues.getT(4));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$256(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(16479078).iconSet(MaterialIconSet.DULL);
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 4, Materials.Hydrogen, 9, Materials.Oxygen, 1, Materials.Potassium, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$257(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(11494209).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Caesium, 1, Materials.Oxygen, 1, Materials.Hydrogen, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$258(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(6477043).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Caesium, 2, Materials.Carbon, 1, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$259(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.color(13290184).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Sodium, 1, Materials.Hydrogen, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$260(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.ROUGH);
      Object[] var1 = new Object[]{Materials.Vanadium, 2, Materials.Oxygen, 5};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$261(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(9612543).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Actinium, 1, Materials.Carbon, 4, Materials.Oxygen, 8};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$262(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(14016511).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Actinium, 1, Materials.Hydrogen, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.FORCE_GENERATE_BLOCK};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$264$lambda$263(FluidBuilder $this$plasma) {
      Intrinsics.checkNotNullParameter($this$plasma, "$this$plasma");
      $this$plasma.temperature(500000);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$264(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      MaterialExtKt.plasma($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$264$lambda$263);
      $this$addMaterial.color(Materials.Actinium.getMaterialRGB() * 9 / 8).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Actinium, 1, Materials.Hydrogen, 12};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$265(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(4287680).iconSet(MaterialIconSet.ROUGH);
      Object[] var1 = new Object[]{Materials.Carbon, 1, Materials.Hydrogen, 1, Materials.Oxygen, 2, Materials.Sodium, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$266(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(1324711).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Sodium, 2, Materials.Sulfur, 2, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$267(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(6856292).iconSet(MaterialIconSet.DULL);
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 4, Materials.Hydrogen, 4, Materials.Sulfur, 2, Materials.Lithium, 2, Materials.Selenium, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$268(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(10021267).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      Object[] var2 = new Object[]{Materials.Rhenium, 1, Materials.Carbon, 10, Materials.Hydrogen, 8, Materials.Sulfur, 4, Materials.Selenium, 4, Materials.Oxygen, 4};
      $this$addMaterial.components(var2);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$269(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gas();
      $this$addMaterial.color(16711808);
      MaterialFlag[] var1 = new Object[]{Materials.Helium, 1, Materials.Neon, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$270(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(7895160);
      MaterialFlag[] var1 = new Object[]{Materials.Germanium, 1, Materials.Chlorine, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$271(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(5987194);
      MaterialFlag[] var1 = new Object[]{Materials.Silicon, 1, Materials.Chlorine, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$272(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(122984).iconSet(MaterialIconSet.MAGNETIC);
      MaterialFlag[] var1 = new Object[]{Materials.Caesium, 1, Materials.Cerium, 1, Materials.Cobalt, 2, Materials.Indium, 10};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.EXT_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, GTLiteMaterialFlags.NO_ALLOY_BLAST_RECIPES, MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE, MaterialFlags.GENERATE_BOLT_SCREW};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 8900, GasTier.HIGH, GTValues.VA[8], GTLiteValues.getS(34), GTValues.VA[7], GTLiteValues.getS(17));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$273(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(8535168).iconSet(MaterialIconSet.BRIGHT);
      Collection var10001 = (Collection)Materials.STD_METAL;
      MaterialFlag[] var1 = new MaterialFlag[]{MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE};
      $this$addMaterial.flags(var10001, var1);
      Object[] var2 = new Object[]{Materials.Mercury, 2, Materials.Cadmium, 1, Materials.Tellurium, 2};
      $this$addMaterial.components(var2);
      MaterialExtKt.blastProp($this$addMaterial, 12170, GasTier.HIGHER, GTValues.VA[9], GTLiteValues.getS(12), GTValues.VA[8], GTLiteValues.getS(6));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$274(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(3955893).iconSet(MaterialIconSet.MAGNETIC);
      MaterialFlag[] var1 = new Object[]{Materials.Thallium, 1, Materials.Roentgenium, 1, Materials.Chlorine, 3};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.EXT_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE, MaterialFlags.GENERATE_RING};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 12500, GasTier.HIGHEST, GTValues.VA[8], GTLiteValues.getS(48), GTValues.VA[7], GTLiteValues.getS(24));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$275(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(7434430).iconSet(MaterialIconSet.DULL);
      MaterialFlag[] var1 = new Object[]{Materials.SiliconDioxide, 1, Materials.SodiumHydroxide, 1, Materials.HydrochloricAcid, 1, Materials.Water, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$276(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(9868797).iconSet(GTLiteMaterialIconSet.NANOPARTICLES);
      MaterialFlag[] var1 = new Object[]{Materials.SiliconDioxide, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$277(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(7894089).iconSet(MaterialIconSet.DULL);
      MaterialFlag[] var1 = new Object[]{Materials.Nitrogen, 1, Materials.Oxygen, 2, Materials.Boron, 1, Materials.Fluorine, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$278(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(10693260).iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Nitrogen, 1, Materials.Oxygen, 1, Materials.Boron, 1, Materials.Fluorine, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$280$lambda$279(FluidBuilder $this$liquid) {
      Intrinsics.checkNotNullParameter($this$liquid, "$this$liquid");
      FluidAttribute[] var1 = new FluidAttribute[]{FluidAttributes.ACID};
      $this$liquid.attributes(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$280(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      MaterialExtKt.liquid($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$280$lambda$279);
      $this$addMaterial.color(8628017);
      MaterialFlag[] var1 = new Object[]{Materials.Hydrogen, 1, Materials.Boron, 1, Materials.Fluorine, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$281(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(7648681).iconSet(MaterialIconSet.METALLIC);
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 1, Materials.Hydrogen, 3, Materials.Oxygen, 1, Materials.Potassium, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$282(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(5789940).iconSet(MaterialIconSet.METALLIC);
      MaterialFlag[] var1 = new Object[]{Materials.Nitrogen, 4, Materials.Hydrogen, 8, Materials.Sulfur, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$283(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(8161753).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 1, Materials.Hydrogen, 8, Materials.Oxygen, 3, Materials.Nitrogen, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$284(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(6580354).iconSet(MaterialIconSet.METALLIC);
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 2, Materials.Hydrogen, 7, Materials.Oxygen, 2, Materials.Nitrogen, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$285(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Sodium, 1, Materials.Nitrogen, 1, Materials.Hydrogen, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$286(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage();
      MaterialFlag[] var1 = new Object[]{Materials.Sodium, 1, Materials.Nitrogen, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$287(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(12485024).iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 51, Materials.Hydrogen, 42, Materials.Oxygen, 3, Materials.Palladium, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$288(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(15839311).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Potassium, 2, Materials.Platinum, 1, Materials.Chlorine, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$289(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(4342455);
      MaterialFlag[] var1 = new Object[]{Materials.Nitrogen, 2, Materials.Hydrogen, 8, Materials.Sulfur, 2, Materials.Oxygen, 8};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$290(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(8486948);
      MaterialFlag[] var1 = new Object[]{Materials.Silver, 1, Materials.Boron, 1, Materials.Fluorine, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$291(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(9276813).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Silver, 1, Materials.Chlorine, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$292(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(10790052);
      Object[] var1 = new Object[]{Materials.Silver, 2, Materials.Oxygen, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$294$lambda$293(FluidBuilder $this$plasma) {
      Intrinsics.checkNotNullParameter($this$plasma, "$this$plasma");
      $this$plasma.temperature(400000000);
      $this$plasma.translation("gregtech.fluid.generic");
      $this$plasma.customStill();
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$294(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      MaterialExtKt.plasma($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$294$lambda$293);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$296$lambda$295(FluidBuilder $this$liquid) {
      Intrinsics.checkNotNullParameter($this$liquid, "$this$liquid");
      $this$liquid.temperature(18240);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$296(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      MaterialExtKt.liquid($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$296$lambda$295);
      $this$addMaterial.color(8101624);
      MaterialFlag[] var1 = new Object[]{Materials.Deuterium, 2, GTLiteMaterials.INSTANCE.getMetastableHassium(), 1, GTLiteMaterials.INSTANCE.getMetastableFlerovium(), 1, GTLiteMaterials.INSTANCE.getMetastableOganesson(), 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$298$lambda$297(FluidBuilder $this$plasma) {
      Intrinsics.checkNotNullParameter($this$plasma, "$this$plasma");
      $this$plasma.temperature(1600000000);
      $this$plasma.customStill();
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$298(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.liquid();
      MaterialExtKt.plasma($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$298$lambda$297);
      $this$addMaterial.color(6143290).iconSet(MaterialIconSet.BRIGHT);
      Collection var10001 = (Collection)Materials.EXT2_METAL;
      MaterialFlag[] var1 = new MaterialFlag[]{MaterialFlags.GENERATE_DOUBLE_PLATE, MaterialFlags.GENERATE_DENSE, MaterialFlags.GENERATE_RING, MaterialFlags.GENERATE_ROTOR, MaterialFlags.GENERATE_GEAR, MaterialFlags.GENERATE_SMALL_GEAR, MaterialFlags.GENERATE_FRAME, MaterialFlags.GENERATE_ROUND, MaterialFlags.GENERATE_SPRING, MaterialFlags.GENERATE_SPRING_SMALL, MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.rotorProp($this$addMaterial, 48.0F, 16.0F, 983040);
      MaterialExtKt.fluidPipeProp($this$addMaterial, 200000, 10000, true, true, true, true);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$299(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.liquid();
      $this$addMaterial.color(15771461).iconSet(MaterialIconSet.BRIGHT);
      Collection var10001 = (Collection)Materials.EXT2_METAL;
      MaterialFlag[] var1 = new MaterialFlag[]{MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE, MaterialFlags.GENERATE_DOUBLE_PLATE, MaterialFlags.GENERATE_FRAME};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.cableProp$default($this$addMaterial, GTValues.V[11], 24, 12, false, 8, (Object)null);
      MaterialExtKt.fluidPipeProp($this$addMaterial, 400000, 20000, true, true, true, true);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$300(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(Materials.Sodium.getMaterialRGB() + Materials.Ethanol.getMaterialRGB());
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 2, Materials.Hydrogen, 5, Materials.Oxygen, 1, Materials.Sodium, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$301(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(Materials.Iron3Chloride.getMaterialRGB() - 10);
      Object[] var1 = new Object[]{Materials.Iron, 1, Materials.Chlorine, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$302(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(4239072).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 73, Materials.Hydrogen, 15, Materials.Nitrogen, 1, Materials.Iron, 1, Materials.Palladium, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$303(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.colorAverage();
      MaterialFlag[] var1 = new Object[]{Materials.Lutetium, 2, Materials.Thulium, 2, Materials.Yttrium, 6, Materials.Chlorine, 30, Materials.Water, 15};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$304(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(13408563).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Sodium, 3, Materials.Vanadium, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$305(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.FINE);
      MaterialFlag[] var1 = new Object[]{Materials.Lutetium, 1, Materials.Thulium, 1, GTLiteMaterials.INSTANCE.getSodiumVanadate(), 1, Materials.RareEarth, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$306(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.color(9181987).iconSet(MaterialIconSet.GEM_HORIZONTAL);
      MaterialFlag[] var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS, MaterialFlags.CRYSTALLIZABLE};
      $this$addMaterial.flags(var1);
      Object[] var2 = new Object[]{Materials.Yttrium, 1, Materials.Vanadium, 1, Materials.Oxygen, 1, Materials.Lutetium, 1, Materials.Thulium, 1};
      $this$addMaterial.components(var2);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$307(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.color(9206710).iconSet(MaterialIconSet.GEM_HORIZONTAL);
      MaterialFlag[] var1 = new Object[]{Materials.Silicon, 3, Materials.Nitrogen, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, GTLiteMaterialFlags.DISABLE_CRYSTALLIZATION, MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$308(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.color(4283248).iconSet(MaterialIconSet.RUBY);
      MaterialFlag[] var1 = new Object[]{Materials.Silicon, 3, Materials.Nitrogen, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, GTLiteMaterialFlags.DISABLE_CRYSTALLIZATION, MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$309(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(5735772).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Cerium, 2, Materials.Carbon, 3, Materials.Oxygen, 9};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$311$lambda$310(FluidBuilder $this$liquid) {
      Intrinsics.checkNotNullParameter($this$liquid, "$this$liquid");
      $this$liquid.temperature(209);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$311(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      MaterialExtKt.liquid($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$311$lambda$310);
      $this$addMaterial.color(15084085);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getManganeseDifluoride(), 1, Materials.ZincSulfide, 1, GTLiteMaterials.INSTANCE.getTantalumPentoxide(), 1, Materials.Rutile, 1, Materials.Ethanol, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$313$lambda$312(FluidBuilder $this$liquid) {
      Intrinsics.checkNotNullParameter($this$liquid, "$this$liquid");
      FluidAttribute[] var1 = new FluidAttribute[]{FluidAttributes.ACID};
      $this$liquid.attributes(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$313(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      MaterialExtKt.liquid($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$313$lambda$312);
      $this$addMaterial.color(13743660);
      Object[] var1 = new Object[]{Materials.Hydrogen, 1, Materials.Gold, 1, Materials.Chlorine, 4};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$314(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(13157436).iconSet(MaterialIconSet.METALLIC);
      MaterialFlag[] var1 = new Object[]{Materials.Cadmium, 1, Materials.Sulfur, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING, MaterialFlags.GENERATE_PLATE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$315(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.color(9541965).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Bismuth, 1, Materials.Antimony, 1, Materials.Tellurium, 2, Materials.Sulfur, 1};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.STD_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING, MaterialFlags.GENERATE_FOIL};
      $this$addMaterial.flags(var10001, var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$316(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(1310722).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getPlutonium244(), 1, Materials.Hydrogen, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$317(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.color(2031876).iconSet(MaterialIconSet.MAGNETIC);
      Object[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getPlutonium244(), 1, Materials.Phosphorus, 1};
      $this$addMaterial.components(var1);
      $this$addMaterial.flags((Collection)Materials.STD_METAL, new MaterialFlag[0]);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$318(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gas();
      $this$addMaterial.color(11318064);
      MaterialFlag[] var1 = new Object[]{Materials.Phosphorus, 1, Materials.Hydrogen, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING, MaterialFlags.FLAMMABLE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$319(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.liquid();
      $this$addMaterial.color(3933445).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Rubidium, 11, Materials.Tritanium, 11, GTLiteMaterials.INSTANCE.getAdamantium(), 7, GTLiteMaterials.INSTANCE.getFirestone(), 13, GTLiteMaterials.INSTANCE.getMetastableOganesson(), 13, GTLiteMaterials.INSTANCE.getActiniumSuperhydride(), 8};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.EXT2_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, GTLiteMaterialFlags.NO_ALLOY_BLAST_RECIPES, MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE, GTLiteMaterialFlags.GENERATE_NANITE, MaterialFlags.GENERATE_FRAME, MaterialFlags.GENERATE_DOUBLE_PLATE, MaterialFlags.GENERATE_DENSE, MaterialFlags.GENERATE_FRAME, MaterialFlags.GENERATE_ROTOR};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 22000, GasTier.HIGHEST, GTValues.VA[12], GTLiteValues.getS(20), GTValues.VA[11], GTLiteValues.getS(10));
      MaterialExtKt.cableProp$default($this$addMaterial, GTValues.V[12], 48, 12, false, 8, (Object)null);
      MaterialExtKt.itemPipeProp($this$addMaterial, 16, 2048.0F);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$320(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(13790606).iconSet(MaterialIconSet.BRIGHT);
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 120, Materials.Hydrogen, 60, Materials.Lanthanum, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$321(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(8716204).iconSet(MaterialIconSet.BRIGHT);
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 120, Materials.Lanthanum, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$322(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.polymer();
      $this$addMaterial.color(13780083).iconSet(MaterialIconSet.BRIGHT);
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 108, Materials.Lanthanum, 1};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.EXT2_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var10001, var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$323(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.polymer();
      $this$addMaterial.color(2894988).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 48, Materials.Seaborgium, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$324(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.colorAverage();
      MaterialFlag[] var1 = new Object[]{Materials.Praseodymium, 2, Materials.Nitrogen, 30, Materials.Oxygen, 105, Materials.Holmium, 2, Materials.Yttrium, 6, Materials.Hydrogen, 30};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$325(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.color(Materials.Praseodymium.getMaterialRGB() + Materials.Holmium.getMaterialRGB() + Materials.Yttrium.getMaterialRGB() + Materials.Lithium.getMaterialRGB()).iconSet(MaterialIconSet.RUBY);
      MaterialFlag[] var1 = new Object[]{Materials.Praseodymium, 1, Materials.Holmium, 1, Materials.Lithium, 1, Materials.Yttrium, 1, Materials.Fluorine, 4};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LENS, MaterialFlags.CRYSTALLIZABLE};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$327$lambda$326(FluidBuilder $this$liquid) {
      Intrinsics.checkNotNullParameter($this$liquid, "$this$liquid");
      $this$liquid.temperature(2000000000);
      $this$liquid.translation("gregtech.fluid.generic");
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$327(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      MaterialExtKt.liquid($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$327$lambda$326);
      $this$addMaterial.iconSet(GTLiteMaterialIconSet.HARMONIC);
      Collection var10001 = (Collection)Materials.EXT2_METAL;
      MaterialFlag[] var1 = new MaterialFlag[]{MaterialFlags.GENERATE_DOUBLE_PLATE, MaterialFlags.GENERATE_DENSE, MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE, MaterialFlags.GENERATE_FRAME, MaterialFlags.GENERATE_GEAR};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 26000, GasTier.HIGHEST, GTValues.VA[12], GTLiteValues.getMin(2), GTValues.VA[12], GTLiteValues.getS(30));
      MaterialExtKt.cableProp$default($this$addMaterial, GTValues.V[12], 72, 18, false, 8, (Object)null);
      MaterialExtKt.fluidPipeProp($this$addMaterial, 1600000, 80000, true, true, true, true);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$328(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(15073522).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 10, Materials.Hydrogen, 10, Materials.Chlorine, 3, Materials.Titanium, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$329(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(16777215).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Silver, 1, Materials.Chlorine, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$330(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.liquid();
      $this$addMaterial.color(6167849);
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 149, Materials.Hydrogen, 97, Materials.Nitrogen, 10, Materials.Oxygen, 2, Materials.Titanium, 1, Materials.Boron, 1, Materials.Fluorine, 20};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$331(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(14924279).iconSet(MaterialIconSet.ROUGH);
      Object[] var1 = new Object[]{Materials.Sodium, 1, Materials.Bromine, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$332(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(0).iconSet(MaterialIconSet.METALLIC);
      Collection var10001 = (Collection)Materials.EXT2_METAL;
      MaterialFlag[] var1 = new MaterialFlag[]{MaterialFlags.GENERATE_GEAR, MaterialFlags.GENERATE_SMALL_GEAR, MaterialFlags.GENERATE_SPRING, MaterialFlags.GENERATE_SPRING_SMALL, MaterialFlags.GENERATE_DOUBLE_PLATE, MaterialFlags.GENERATE_DENSE, MaterialFlags.GENERATE_RING, MaterialFlags.GENERATE_ROTOR, MaterialFlags.GENERATE_FRAME, MaterialFlags.GENERATE_ROUND, GTLiteMaterialFlags.GENERATE_NANITE};
      $this$addMaterial.flags(var10001, var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$333(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.iconSet(GTLiteMaterialIconSet.WHITE_DWARF);
      Collection var10001 = (Collection)Materials.EXT2_METAL;
      MaterialFlag[] var1 = new MaterialFlag[]{MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE, MaterialFlags.GENERATE_GEAR, MaterialFlags.GENERATE_FRAME, GTLiteMaterialFlags.GENERATE_NANITE, MaterialFlags.GENERATE_ROTOR};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.cableProp$default($this$addMaterial, GTValues.V[13], 108, 36, false, 8, (Object)null);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$334(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(27058).iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Potassium, 4, Materials.Iron, 1, Materials.Carbon, 6, Materials.Nitrogen, 6, Materials.Water, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$335(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(12627).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Iron, 7, Materials.Carbon, 18, Materials.Nitrogen, 18};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$336(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(6750054).iconSet(MaterialIconSet.ROUGH);
      Object[] var1 = new Object[]{Materials.Copper, 3, Materials.Arsenic, 2, Materials.Oxygen, 8};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$337(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.liquid();
      $this$addMaterial.color(65280).iconSet(MaterialIconSet.DULL);
      MaterialFlag[] var1 = new Object[]{Materials.Arsenic, 1, Materials.Copper, 1, Materials.Hydrogen, 1, Materials.Oxygen, 3};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$338(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Caesium, 1, Materials.Iodine, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$339(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getCaesiumIodide(), 1, Materials.Thallium, 1, Materials.Thulium, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$340(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      int[] var1 = new int[]{Materials.Cadmium.getMaterialRGB(), Materials.Tungsten.getMaterialRGB()};
      MaterialExtKt.colorAverage($this$addMaterial, var1).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var2 = new Object[]{Materials.Cadmium, 1, Materials.Tungsten, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var2);
      var2 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var2);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$341(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      Material[] var1 = new Material[]{Materials.Bismuth, Materials.Germanium};
      MaterialExtKt.colorAverage($this$addMaterial, var1).iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var2 = new Object[]{Materials.Bismuth, 4, Materials.Germanium, 3, Materials.Oxygen, 12};
      $this$addMaterial.components(var2);
      var2 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var2);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$342(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(15374853).iconSet(MaterialIconSet.METALLIC);
      MaterialFlag[] var1 = new Object[]{Materials.Lanthanum, 2, Materials.Zirconium, 2, Materials.Oxygen, 7};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.STD_METAL;
      var1 = new MaterialFlag[]{GTLiteMaterialFlags.NO_ALLOY_BLAST_RECIPES, MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 11900, GasTier.HIGHEST, GTValues.VA[9], GTLiteValues.getS(35), GTValues.VA[8], GTLiteValues.getS(17) + GTLiteValues.getT(10));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$343(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(4688491).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Moscovium, 2, Materials.Iridium, 2, Materials.Oxygen, 7};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.STD_METAL;
      var1 = new MaterialFlag[]{GTLiteMaterialFlags.NO_ALLOY_BLAST_RECIPES, MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 12200, GasTier.HIGHEST, GTValues.VA[9], GTLiteValues.getS(28), GTValues.VA[8], GTLiteValues.getS(14));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$345$lambda$344(FluidBuilder $this$liquid) {
      Intrinsics.checkNotNullParameter($this$liquid, "$this$liquid");
      $this$liquid.attribute(FluidAttributes.ACID);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$345(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      MaterialExtKt.liquid($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$345$lambda$344);
      Object[] var1 = new Object[]{Materials.Hydrogen, 1, Materials.Moscovium, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$346(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.fluid();
      $this$addMaterial.color(12462921).iconSet(MaterialIconSet.METALLIC);
      MaterialFlag[] var1 = new Object[]{Materials.Strontium, 1, Materials.Europium, 1, Materials.Nihonium, 2, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.STD_METAL;
      var1 = new MaterialFlag[]{GTLiteMaterialFlags.NO_ALLOY_BLAST_RECIPES, MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE};
      $this$addMaterial.flags(var10001, var1);
      MaterialExtKt.blastProp($this$addMaterial, 12400, GasTier.HIGHEST, GTValues.VA[9], GTLiteValues.getS(33), GTValues.VA[8], GTLiteValues.getS(12) + GTLiteValues.getT(15));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$347(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.polymer();
      $this$addMaterial.liquid();
      $this$addMaterial.color(14671839).iconSet(MaterialIconSet.SHINY);
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 48, Materials.Neutronium, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_FOIL};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$348(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.polymer();
      $this$addMaterial.liquid();
      $this$addMaterial.plasma();
      $this$addMaterial.color(10361295);
      Collection var10001 = (Collection)Materials.STD_METAL;
      MaterialFlag[] var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.NO_SMASHING, MaterialFlags.NO_SMELTING, MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE};
      $this$addMaterial.flags(var10001, var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$349(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(Materials.Silicon.getMaterialRGB()).iconSet(GTLiteMaterialIconSet.NANOPARTICLES);
      MaterialFlag[] var1 = new Object[]{Materials.Silicon, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$350(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(6697779).iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 6, Materials.Hydrogen, 7, Materials.Oxygen, 6, Materials.Sodium, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$351(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(6636321).iconSet(MaterialIconSet.ROUGH);
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 12, Materials.Hydrogen, 14, Materials.Oxygen, 12, Materials.Calcium, 2};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$352(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.liquid();
      $this$addMaterial.iconSet(GTLiteMaterialIconSet.CHROMATIC);
      MaterialFlag[] var1 = new Object[]{Materials.Glass, 64};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.STD_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION, MaterialFlags.GENERATE_FOIL, MaterialFlags.GENERATE_FINE_WIRE, MaterialFlags.GENERATE_LENS, MaterialFlags.GENERATE_FRAME};
      $this$addMaterial.flags(var10001, var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$353(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.color(9203320).iconSet(MaterialIconSet.GEM_HORIZONTAL);
      MaterialFlag[] var1 = new Object[]{Materials.Lanthanum, 1, Materials.Boron, 6};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.STD_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.GENERATE_LENS, MaterialFlags.CRYSTALLIZABLE};
      $this$addMaterial.flags(var10001, var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$354(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Francium, 2, Materials.Carbon, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$355(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.gem();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.GEM_HORIZONTAL);
      MaterialFlag[] var1 = new Object[]{Materials.Boron, 4, Materials.Carbon, 1};
      $this$addMaterial.components(var1);
      Collection var10001 = (Collection)Materials.STD_METAL;
      var1 = new MaterialFlag[]{MaterialFlags.GENERATE_LENS};
      $this$addMaterial.flags(var10001, var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$356(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Astatine, 1, Materials.Nitrogen, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$357(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(15576599).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Holmium, 1, Materials.Iodine, 3};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$358(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(1205503).iconSet(MaterialIconSet.MAGNETIC);
      Object[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getFranciumCarbide(), 1, GTLiteMaterials.INSTANCE.getBoronCarbide(), 1, GTLiteMaterials.INSTANCE.getAstatineAzide(), 1, GTLiteMaterials.INSTANCE.getHolmiumIodide(), 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$359(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(11546177).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Plutonium241, 1, Materials.Oxygen, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$360(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.DULL);
      Object[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getPlutoniumDioxide(), 1, Materials.Uraninite, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$361(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.ROUGH);
      Object[] var1 = new Object[]{Materials.Barium, 1, Materials.Manganese, 1, Materials.Oxygen, 4};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$362(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.colorAverage().iconSet(MaterialIconSet.DULL);
      Object[] var1 = new Object[]{Materials.Barium, 1, Materials.Chlorine, 2};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$363(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(16741385).iconSet(MaterialIconSet.METALLIC);
      Object[] var1 = new Object[]{Materials.Thallium, 1, Materials.Chlorine, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$364(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(206161).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{GTLiteMaterials.INSTANCE.getMetastableHassium(), 1, Materials.Chlorine, 4};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$365(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(5969792).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Rhenium, 1, Materials.Chlorine, 5};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$367$lambda$366(FluidBuilder $this$liquid) {
      Intrinsics.checkNotNullParameter($this$liquid, "$this$liquid");
      FluidAttribute[] var1 = new FluidAttribute[]{FluidAttributes.ACID};
      $this$liquid.attributes(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$367(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      MaterialExtKt.liquid($this$addMaterial, GTLiteFirstDegreeMaterials::init$lambda$367$lambda$366);
      $this$addMaterial.color(16566146);
      Object[] var1 = new Object[]{Materials.Hydrogen, 1, Materials.Phosphorus, 1, Materials.Fluorine, 6};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$368(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(8486994);
      Object[] var1 = new Object[]{Materials.Sodium, 1, Materials.Sulfur, 1, Materials.Carbon, 1, Materials.Nitrogen, 1};
      $this$addMaterial.components(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$369(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.dust();
      $this$addMaterial.color(6250370).iconSet(MaterialIconSet.BRIGHT);
      MaterialFlag[] var1 = new Object[]{Materials.Carbon, 60, Materials.Hydrogen, 84, Materials.Oxygen, 12, Materials.Nitrogen, 12, Materials.Sulfur, 6, Materials.Fluorine, 6, Materials.Phosphorus, 1, Materials.Rhenium, 1, GTLiteMaterials.INSTANCE.getMetastableHassium(), 1, Materials.Thallium, 1};
      $this$addMaterial.components(var1);
      var1 = new MaterialFlag[]{MaterialFlags.DISABLE_DECOMPOSITION};
      $this$addMaterial.flags(var1);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$370(Material.Builder $this$addMaterial) {
      Intrinsics.checkNotNullParameter($this$addMaterial, "$this$addMaterial");
      $this$addMaterial.ingot();
      $this$addMaterial.color(5263460).iconSet(MaterialIconSet.SHINY);
      Object[] var1 = new Object[]{Materials.Silicon, 1};
      $this$addMaterial.components(var1);
      $this$addMaterial.flags((Collection)Materials.STD_METAL, new MaterialFlag[0]);
      MaterialExtKt.blastProp($this$addMaterial, 3325, GasTier.LOW, GTValues.VA[3], GTLiteValues.getS(45), GTValues.VA[2], GTLiteValues.getS(22) + GTLiteValues.getT(10));
      $this$addMaterial.ingotSmeltInto(Materials.Silicon);
      $this$addMaterial.arcSmeltInto(Materials.Silicon);
      return Unit.INSTANCE;
   }
}
