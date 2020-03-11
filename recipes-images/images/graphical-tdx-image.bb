require console-tdx-image.bb

SUMMARY = "Toradex Embedded Linux Graphical Image"
DESCRIPTION = "Image with a graphical interface, either using weston or X11"

LICENSE = "MIT"

#Prefix to the resulting deployable tarball name
export IMAGE_BASENAME = "Graphical-Image"

SYSTEMD_DEFAULT_TARGET = "graphical.target"

IMAGE_FEATURES += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', \
       bb.utils.contains('DISTRO_FEATURES',     'x11', 'x11', \
                                                       '', d), d)} \
"

IMAGE_INSTALL += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', \
                         'weston weston-init weston-examples wayland-terminal-launch', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', \
                         'weston-xwayland xterm', \
      bb.utils.contains('DISTRO_FEATURES', 'x11', 'x-window-xterm', '', d), d)} \
"
