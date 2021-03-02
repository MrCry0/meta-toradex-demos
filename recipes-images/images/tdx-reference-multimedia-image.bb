require tdx-reference-minimal-image.bb

SUMMARY = "Toradex Embedded Linux Reference Multimedia Image"
DESCRIPTION = "Image for BSP verification with QT and multimedia features"

inherit populate_sdk_qt5

#Prefix to the resulting deployable tarball name
export IMAGE_BASENAME = "Reference-Multimedia-Image"

# Show Tezi EULA license
TEZI_SHOW_EULA_LICENSE ?= "1"

SYSTEMD_DEFAULT_TARGET = "graphical.target"

IMAGE_FEATURES += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', \
       bb.utils.contains('DISTRO_FEATURES',     'x11', 'x11', \
                                                       '', d), d)} \
"

APP_LAUNCH_WAYLAND ?= "wayland-qtdemo-launch-cinematicexperience"
APP_LAUNCH_WAYLAND_colibri-imx6ull ?= "wayland-qtdemo-launch-analogclock"
APP_LAUNCH_WAYLAND_colibri-imx7 ?= "wayland-qtdemo-launch-analogclock"
APP_LAUNCH_WAYLAND_colibri-imx7-emmc ?= "wayland-qtdemo-launch-analogclock"
APP_LAUNCH_WAYLAND_apalis-tk1 ?= "wayland-qtdemo-launch-qtsmarthome"

APP_LAUNCH_X11 ?= "x-window-qtcinematicexperience"
APP_LAUNCH_X11_colibri-imx6ull ?= "x-window-analogclock"
APP_LAUNCH_X11_colibri-imx7 ?= "x-window-analogclock"
APP_LAUNCH_X11_colibri-imx7-emmc ?= "x-window-analogclock"
APP_LAUNCH_X11_apalis-tk1 ?= "x-window-qtsmarthome"

IMAGE_INSTALL += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', \
                         'weston weston-init weston-examples ${APP_LAUNCH_WAYLAND}', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', \
                         'weston-xwayland xterm', \
       bb.utils.contains('DISTRO_FEATURES', 'x11', '${APP_LAUNCH_X11}', '', d), d)} \
    \
    packagegroup-tdx-cli \
    packagegroup-tdx-graphical \
    packagegroup-tdx-qt5 \
    \
    bash \
    coreutils \
    less \
    makedevs \
    mime-support \
    util-linux \
    v4l-utils \
    \
    gpicview \
    media-files \
"
