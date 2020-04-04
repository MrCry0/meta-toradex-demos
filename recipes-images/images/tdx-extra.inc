#additional packages to include in our Toradex images

# Copy Licenses to image /usr/share/common-licenses, on vybrid for flash size reasons only the manifest
COPY_LIC_MANIFEST ?= "1"
#Bug with several MACHINES build, license files for all arch packages stored wrongly
COPY_LIC_DIRS ?= "1"

add_rootfs_version () {
    echo -n "${IMAGE_NAME} ${PV}" >> ${IMAGE_ROOTFS}/etc/issue
    echo " ${TDX_VERDATE}" | sed 's/-//' >>${IMAGE_ROOTFS}/etc/issue
    echo "" >> ${IMAGE_ROOTFS}/etc/issue
}

PV = "${TDX_VER_PACKAGE_MIN}"

# add the rootfs version to the welcome banner
ROOTFS_POSTPROCESS_COMMAND += " add_rootfs_version;"

# Entropy source daemon
RANDOM_HELPER = "rng-tools"
RANDOM_HELPER_tegra124 = "haveged"
RANDOM_HELPER_tegra124m = "haveged"

IMAGE_INSTALL_append_tegra124 = " \
    aspell \
    e2fsprogs \
    e2fsprogs-mke2fs \
    e2fsprogs-tune2fs \
    file \
    fs-init \
    hdmi-hotplug \
    kernel-modules \
    linuxptp \
    lvds-tegra124 \
    pciutils \
    ptpd \
    tegrastats \
    u-boot-fw-utils \
    usb-rndis-systemd \
    perf \
"

IMAGE_INSTALL_remove_tegra124 = "\
    backports \
"

IMAGE_INSTALL_append_tegra124m = " \
    aspell \
    e2fsprogs \
    e2fsprogs-mke2fs \
    e2fsprogs-tune2fs \
    file \
    fs-init \
    hdmi-hotplug \
    kernel-modules \
    linuxptp \
    lvds-tegra124 \
    pciutils \
    ptpd \
    u-boot-fw-utils \
    perf \
"

#note that the kernel-modules meta package gets installed from
#meta-fsl-arm/conf/machine/include/imx-base.inc
IMAGE_INSTALL-MX6QDL = " \
    aspell \
    bmode-usb \
    e2fsprogs \
    e2fsprogs-mke2fs \
    e2fsprogs-resize2fs \
    e2fsprogs-tune2fs \
    file \
    fs-init \
    gpio-export \
    libusbgx \
    libusbgx-examples \
    linuxptp \
    mimetypes \
    perf \
    ptpd \
    u-boot-fw-utils \
"
IMAGE_INSTALL_append_mx6q = "${IMAGE_INSTALL-MX6QDL}"
IMAGE_INSTALL_append_mx6dl = "${IMAGE_INSTALL-MX6QDL}"
IMAGE_INSTALL_append_apalis-imx6 = " \
    pciutils \
"

IMAGE_INSTALL_append_mx6ull = " \
    bmode-usb \
    libusbgx \
    libusbgx-examples \
    linuxptp \
    ptpd \
    u-boot-fw-utils \
"
IMAGE_INSTALL_append_colibri-imx6ull = " \
    mtd-utils-ubifs \
    mwifiexap \
    bluez-alsa \
"

IMAGE_INSTALL_append_mx7 = " \
    aspell \
    file \
    libusbgx \
    libusbgx-examples \
    linuxptp \
    ptpd \
    u-boot-fw-utils \
"
IMAGE_INSTALL_append_colibri-imx7-emmc = " \
    perf \
"
IMAGE_INSTALL_append_colibri-imx7 = " \
    mtd-utils-ubifs \
"

IMAGE_INSTALL_append_mx8 = " \
    aspell \
    bmode-usb \
    clpeak \
    cpuburn-a53 \
    e2fsprogs \
    e2fsprogs-mke2fs \
    e2fsprogs-resize2fs \
    e2fsprogs-tune2fs \
    file \
    fs-init \
    libusbgx \
    libusbgx-examples \
    linuxptp \
    pciutils \
    perf \
    ptpd \
    u-boot-fw-utils \
"

IMAGE_INSTALL += " \
    packagegroup-dotnet-deps \
    \
    \
    alsa-utils \
    alsa-utils-aplay \
    alsa-utils-amixer \
    \
    libgpiod-tools \
    sqlite3 \
    \
    ${BACKPORTS_WIFI} \
    avahi-autoipd \
    ppp \
    curl \
    can-utils iproute2 \
    nfs-utils-client \
    \
    bzip2 \
    gdbserver \
    grep \
    joe \
    minicom \
    ldd \
    lsof \
    mtd-utils \
    dosfstools \
    ${RANDOM_HELPER} \
    util-linux \
    util-linux-fstrim \
    \
    devmem2 \
    ethtool \
    evtest \
    hdparm \
    iperf3 \
    i2c-tools \
    libsoc \
    lmbench \
    memtester \
    mmc-utils-cos \
    nbench-byte \
    rt-tests \
    procps \
    stress \
    strace \
    tinymembench \
    util-linux-lsblk \
    \
    tdx-oak-sensors \
    udev-toradex-rules \
"

# Wi-Fi backports, firmware and packages

BACKPORTS_WIFI = " \
    backports                \
    hostapd                  \
    linux-firmware-ath10k    \
    linux-firmware-sd8686    \
    linux-firmware-sd8688    \
    linux-firmware-sd8787    \
    linux-firmware-sd8797    \
    linux-firmware-sd8887    \
    linux-firmware-sd8997    \
    linux-firmware-ralink    \
    linux-firmware-rtl8192cu \
    linux-firmware-rtl8188eu \
    wireless-regdb-static    \
"
WIFI_EXTRA = " \
    linux-firmware-ath9k     \
    linux-firmware-iwlwifi   \
"

IMAGE_INSTALL_append_apalis-tk1 = " \
    ${WIFI_EXTRA} \
"

IMAGE_INSTALL_append_apalis-tk1-mainline = " \
    ${WIFI_EXTRA} \
"

IMAGE_INSTALL_append_apalis-imx6 = " \
    ${WIFI_EXTRA} \
"

IMAGE_INSTALL_append_mx8 = " \
    bluez-alsa \
    mwifiexap \
    ${WIFI_EXTRA} \
"
