SUMMARY = "Toradex Embedded Linux Qt5 Demo With X11"
SUMMARY_append_apalis-tk1-mainline = " (Mainline)"
DESCRIPTION = "Image with the Qt5 Framework and the X11 server"

# after the boot systemd starts X and then a qt5 application, check
# recipes-graphics/x-window-simple-app/x-window-qtsmarthome and
# https://developer.toradex.com/knowledge-base/how-to-autorun-application-at-the-start-up-in-linux#X11_with_One_User_Application
# for how this is done.

LICENSE = "MIT"

#start of the resulting deployable tarball name
export IMAGE_BASENAME = "Qt5-X11-Image"
IMAGE_NAME_apalis-imx6 = "Apalis-iMX6_${IMAGE_BASENAME}"
IMAGE_NAME_apalis-tk1 = "Apalis-TK1_${IMAGE_BASENAME}"
IMAGE_NAME_apalis-tk1-mainline = "Apalis-TK1-Mainline_${IMAGE_BASENAME}"
IMAGE_NAME_colibri-imx6 = "Colibri-iMX6_${IMAGE_BASENAME}"
IMAGE_NAME_colibri-imx6ull = "Colibri-iMX6ULL_${IMAGE_BASENAME}"
IMAGE_NAME_colibri-imx7 = "Colibri-iMX7_${IMAGE_BASENAME}"
IMAGE_NAME_colibri-imx7-emmc = "Colibri-iMX7-eMMC_${IMAGE_BASENAME}"
IMAGE_NAME_colibri-pxa = "Colibri-PXA_${IMAGE_BASENAME}"
IMAGE_NAME = "${MACHINE}_${IMAGE_BASENAME}"

IMAGE_FEATURES += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', \
       bb.utils.contains('DISTRO_FEATURES',     'x11', 'x11', \
                                                       '', d), d)} \
"

SYSTEMD_DEFAULT_TARGET = "graphical.target"

inherit populate_sdk_qt5

IMAGE_LINGUAS = "en-us"
#IMAGE_LINGUAS = "de-de fr-fr en-gb en-us pt-br es-es kn-in ml-in ta-in"
#ROOTFS_POSTPROCESS_COMMAND += 'install_linguas; '

DISTRO_UPDATE_ALTERNATIVES ??= ""
ROOTFS_PKGMANAGE_PKGS ?= '${@oe.utils.conditional("ONLINE_PACKAGE_MANAGEMENT", "none", "", "${ROOTFS_PKGMANAGE} ${DISTRO_UPDATE_ALTERNATIVES}", d)}'

CONMANPKGS ?= "connman connman-plugin-loopback connman-plugin-ethernet connman-plugin-wifi connman-client"

# this would pull in a large amount of gst-plugins, we only add a selected few
#    gstreamer1.0-plugins-base-meta
#    gstreamer1.0-plugins-good-meta
#    gstreamer1.0-plugins-bad-meta
#    gst-ffmpeg
GSTREAMER = " \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-base-alsa \
    gstreamer1.0-plugins-base-audioconvert \
    gstreamer1.0-plugins-base-audioresample \
    gstreamer1.0-plugins-base-audiotestsrc \
    gstreamer1.0-plugins-base-typefindfunctions \
    gstreamer1.0-plugins-base-ogg \
    gstreamer1.0-plugins-base-theora \
    gstreamer1.0-plugins-base-videotestsrc \
    gstreamer1.0-plugins-base-vorbis \
    gstreamer1.0-plugins-good-audioparsers \
    gstreamer1.0-plugins-good-autodetect \
    gstreamer1.0-plugins-good-avi \
    gstreamer1.0-plugins-good-deinterlace \
    gstreamer1.0-plugins-good-id3demux \
    gstreamer1.0-plugins-good-isomp4 \
    gstreamer1.0-plugins-good-matroska \
    gstreamer1.0-plugins-good-multifile \
    gstreamer1.0-plugins-good-rtp \
    gstreamer1.0-plugins-good-rtpmanager \
    gstreamer1.0-plugins-good-udp \
    gstreamer1.0-plugins-good-video4linux2 \
    gstreamer1.0-plugins-good-wavenc \
    gstreamer1.0-plugins-good-wavparse \
"
# No longer available
#    gst-plugins-base-decodebin \
#    gst-plugins-base-decodebin2 \
#    gst-plugins-base-playbin \
#    gst-plugins-ugly-asf \
#"

GSTREAMER_MX6QDL = " \
    gstreamer1.0-plugins-base-ximagesink \
    gstreamer1.0-plugins-imx \
"
GSTREAMER_append_mx6q = "${GSTREAMER_MX6QDL}"
GSTREAMER_append_mx6dl = "${GSTREAMER_MX6QDL}"

GSTREAMER_append_mx7 = " \
    gstreamer1.0-plugins-base-ximagesink \
"
# No longer available
#    gst-plugins-gl \
#    gst-fsl-plugin \
#

GSTREAMER_append_tegra124 = " \
    ${@bb.utils.contains("LICENSE_FLAGS_WHITELIST", "commercial", "gstreamer1.0-libav", "", d)} \
    gstreamer1.0-plugins-bad-videoparsersbad \
    gstreamer \
    gst-plugins-base \
    gst-plugins-base-alsa \
    gst-plugins-base-audioconvert \
    gst-plugins-base-audioresample \
    gst-plugins-base-audiotestsrc \
    gst-plugins-base-decodebin \
    gst-plugins-base-decodebin2 \
    gst-plugins-base-playbin \
    gst-plugins-base-typefindfunctions \
    gst-plugins-base-ogg \
    gst-plugins-base-theora \
    gst-plugins-base-videotestsrc \
    gst-plugins-base-vorbis \
    gst-plugins-base-ximagesink \
    gst-plugins-base-xvimagesink \
    gst-plugins-good \
    gst-plugins-good-audioparsers \
    gst-plugins-good-autodetect \
    gst-plugins-good-avi \
    gst-plugins-good-deinterlace \
    gst-plugins-good-id3demux \
    gst-plugins-good-isomp4 \
    gst-plugins-good-matroska \
    gst-plugins-good-rtp \
    gst-plugins-good-rtpmanager \
    gst-plugins-good-udp \
    gst-plugins-good-video4linux2 \
    gst-plugins-good-wavenc \
    gst-plugins-good-wavparse \
    libgstcodecparsers-1.0 \
    libgstnvegl \
    libgstomx-0.10 \
    libgstomx-1.0 \
"

IMAGE_INSTALL_QT5 = " \
    packagegroup-qt5 \
    liberation-fonts \
    x-window-qtsmarthome \
"

IMAGE_INSTALL_append_tegra124 = " \
    libglu \
    mesa-demos \
    freeglut \
    tiff \
    xvinfo \
"
IMAGE_INSTALL_append_tegra124m = " \
    libglu \
    freeglut \
    tiff \
    xvinfo \
"
IMAGE_INSTALL_MX6QDL = " \
    packagegroup-fsl-gpu-libs \
    libopencl-imx \
    eglinfo-x11 \
"
IMAGE_INSTALL_append_mx6q = "${IMAGE_INSTALL_MX6QDL}"
IMAGE_INSTALL_append_mx6dl = "${IMAGE_INSTALL_MX6QDL}"

# Packages which might no longer exist
RRECOMMENDS_${PN} += "xserver-xorg-multimedia-modules"

IMAGE_INSTALL += " \
    ${IMAGE_INSTALL_QT5} \
    \
    xdg-utils \
    \
    libgsf \
    libxres \
    makedevs \
    mime-support \
    xcursor-transparent-theme \
    zeroconf \
    packagegroup-boot \
    packagegroup-basic \
    udev-extra-rules \
    ${CONMANPKGS} \
    ${ROOTFS_PKGMANAGE_PKGS} \
    timestamp-service \
    packagegroup-base-extended \
    xserver-common \
    xserver-xorg-extension-dbe \
    xserver-xorg-extension-extmod \
    xauth \
    xhost \
    xset \
    setxkbmap \
    \
    xrdb \
    xorg-minimal-fonts xserver-xorg-utils \
    scrot \
    unclutter \
    \
    libxdamage libxvmc libxinerama \
    libxcursor \
    \
    bash \
    \
    ${GSTREAMER} \
    v4l-utils \
    libpcre \
    libpcreposix \
    libxcomposite \
    alsa-states \
    \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', \
                         'weston weston-init weston-examples libdrm-tests', \
                         bb.utils.contains('DISTRO_FEATURES', 'x11', \
                                           'xserver-nodm-init', '', d), d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', \
                         'weston-xwayland xterm', '', d)} \
"

require recipes-images/images/tdx-extra.inc

IMAGE_DEV_MANAGER   = "udev"
IMAGE_INIT_MANAGER  = "systemd"
IMAGE_INITSCRIPTS   = " "
IMAGE_LOGIN_MANAGER = "busybox shadow"

inherit core-image
