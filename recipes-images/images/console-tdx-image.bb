inherit core-image

SUMMARY = "Toradex Embedded Linux Console Image"
DESCRIPTION = "Image without graphical interface"

LICENSE = "MIT"

#Prefix to the resulting deployable tarball name
export IMAGE_BASENAME = "Console-Image"
MACHINE_NAME ?= "${MACHINE}"
IMAGE_NAME = "${MACHINE_NAME}_${IMAGE_BASENAME}"

# Copy Licenses to image /usr/share/common-license
COPY_LIC_MANIFEST ?= "1"
COPY_LIC_DIRS ?= "1"

add_rootfs_version () {
    printf "${DISTRO_NAME} ${DISTRO_VERSION} (${DISTRO_CODENAME}) \\\n \\\l\n" > ${IMAGE_ROOTFS}/etc/issue
    printf "${DISTRO_NAME} ${DISTRO_VERSION} (${DISTRO_CODENAME}) %%h\n" > ${IMAGE_ROOTFS}/etc/issue.net
    printf "${IMAGE_NAME}\n\n" >> ${IMAGE_ROOTFS}/etc/issue
    printf "${IMAGE_NAME}\n\n" >> ${IMAGE_ROOTFS}/etc/issue.net
}
# add the rootfs version to the welcome banner
ROOTFS_POSTPROCESS_COMMAND += " add_rootfs_version;"

IMAGE_LINGUAS = "en-us"
#IMAGE_LINGUAS = "de-de fr-fr en-gb en-us pt-br es-es kn-in ml-in ta-in"
#ROOTFS_POSTPROCESS_COMMAND += 'install_linguas; '

ROOTFS_PKGMANAGE_PKGS ?= '${@oe.utils.conditional("ONLINE_PACKAGE_MANAGEMENT", "none", "", "${ROOTFS_PKGMANAGE}", d)}'

CONMANPKGS ?= "connman connman-plugin-loopback connman-plugin-ethernet connman-plugin-wifi connman-client"

IMAGE_INSTALL += " \
    packagegroup-boot \
    packagegroup-basic \
    packagegroup-base-tdx-cli \
    packagegroup-machine-tdx-cli \
    packagegroup-wifi-tdx-cli \
    packagegroup-wifi-fw-tdx-cli \
    udev-extra-rules \
    ${CONMANPKGS} \
    ${ROOTFS_PKGMANAGE_PKGS} \
    timestamp-service \
"

IMAGE_DEV_MANAGER   = "udev"
IMAGE_INIT_MANAGER  = "systemd"
IMAGE_INITSCRIPTS   = " "
IMAGE_LOGIN_MANAGER = "busybox shadow"
