SUMMARY = "The Linux kernels spidev-test programm"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

PACKAGE_ARCH = "${MACHINE_ARCH}"
S = "${WORKDIR}"

do_configure[depends] += "virtual/kernel:do_shared_workdir"

# the spidev_test.c example got moved to tools/spi/ starting with
# the v.4.5.y kernel
KERNELPATH = "tools/spi/spidev_test.c"
KERNELPATH-OLD = "Documentation/spi/spidev_test.c"

do_compile () {
    if [ -f "${STAGING_KERNEL_DIR}/${KERNELPATH}" ]
    then
        ${CC} ${LDFLAGS} ${STAGING_KERNEL_DIR}/${KERNELPATH} -o spidev_test
    else
        ${CC} ${LDFLAGS} ${STAGING_KERNEL_DIR}/${KERNELPATH-OLD} -o spidev_test
    fi
}

do_install () {
    install -d ${D}${bindir}
    install -m 0755 ${S}/spidev_test ${D}${bindir}/
}
