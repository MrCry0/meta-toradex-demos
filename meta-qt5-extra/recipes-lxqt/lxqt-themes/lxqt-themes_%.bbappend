WALLPAPER-MACHINE = "Wallpaper_Toradex.png"
WALLPAPER-MACHINE_colibri-t20 = "Wallpaper_ColibriT20.png"
WALLPAPER-MACHINE_colibri-t30 = "Wallpaper_ColibriT30.png"
WALLPAPER-MACHINE_apalis-t30 = "Wallpaper_ApalisT30.png"
WALLPAPER-MACHINE_apalis-tk1 = "Wallpaper_ApalisTK1.png"
WALLPAPER-MACHINE_colibri-imx6ull = "Wallpaper_ColibriiMX6ULL.png"
WALLPAPER-MACHINE_colibri-imx7-emmc = "Wallpaper_ColibriiMX7D.png"

FILESEXTRAPATHS_prepend := "${THISDIR}/lxqt-themes:"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI += " \
    file://Wallpaper_Toradex.png \
    file://${WALLPAPER-MACHINE} \
"

# for apalis-imx6/colibri-imx6, we decide on the target during postinst
SRC_URI_append_apalis-imx6 += " \
    file://Wallpaper_ApalisiMX6D.png \
    file://Wallpaper_ApalisiMX6Q.png \
"
SRC_URI_append_colibri-imx6 += " \
    file://Wallpaper_ColibriiMX6DL.png \
    file://Wallpaper_ColibriiMX6S.png \
"

# for colibri-imx7 we decide on the target during postinst
SRC_URI_append_colibri-imx7 += " \
    file://Wallpaper_ColibriiMX7D.png \
    file://Wallpaper_ColibriiMX7S.png \
"
SRC_URI_append_colibri-imx7-emmc += " \
    file://Wallpaper_ColibriiMX7D.png \
"

do_install_append () {
    install -m 0755 -d ${D}/${datadir}/lxqt/themes/toradex
    install -m 0644 ${WORKDIR}/Wallpaper*.png ${D}/${datadir}/lxqt/themes/toradex
    ln -sf ${WALLPAPER-MACHINE} ${D}/${datadir}/lxqt/themes/toradex/toradex.png
}

pkg_postinst_ontarget_${PN}_apalis-imx6 () {
    CORES=`grep -c processor /proc/cpuinfo`
    case $CORES in
        4)
            ln -sf Wallpaper_ApalisiMX6Q.png ${datadir}/lxqt/themes/toradex/toradex.png
            ;;
        2)
            ln -sf Wallpaper_ApalisiMX6D.png ${datadir}/lxqt/themes/toradex/toradex.png
            fi
            ;;
        *)
            ln -sf Wallpaper_Toradex.png ${datadir}/lxqt/themes/toradex/toradex.png
            ;;
    esac
}
pkg_postinst_ontarget_${PN}_colibri-imx6 () {
    CORES=`grep -c processor /proc/cpuinfo`
    case $CORES in
        2)
            ln -sf Wallpaper_ColibriiMX6DL.png ${datadir}/lxqt/themes/toradex/toradex.png
            ;;
        1)
            ln -sf Wallpaper_ColibriiMX6S.png ${datadir}/lxqt/themes/toradex/toradex.png
            ;;
        *)
            ln -sf Wallpaper_Toradex.png ${datadir}/lxqt/themes/toradex/toradex.png
            ;;
    esac
}

pkg_postinst_ontarget_${PN}_colibri-imx7 () {
# Currently the soc bus subsystem seems not to work on i.MX 7Solo
#    SOC_TYPE=`cat /sys/bus/soc/devices/soc0/soc_id`
#    if [ "x$SOC_TYPE" = "xi.MX7D" ]; then
#        ln -sf Wallpaper_ColibriiMX7D.png ${datadir}/lxqt/themes/toradex/toradex.png
#    else
#        ln -sf Wallpaper_ColibriiMX7S.png ${datadir}/lxqt/themes/toradex/toradex.png
#    fi
    CORES=`grep -c processor /proc/cpuinfo`
    case $CORES in
        2)
            ln -sf Wallpaper_ColibriiMX7D.png ${datadir}/lxqt/themes/toradex/toradex.png
            ;;
        1)
            ln -sf Wallpaper_ColibriiMX7S.png ${datadir}/lxqt/themes/toradex/toradex.png
            ;;
        *)
            ln -sf Wallpaper_Toradex.png ${datadir}/lxqt/themes/toradex/toradex.png
            ;;
    esac
}
