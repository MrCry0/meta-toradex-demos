FILESEXTRAPATHS_prepend := "${THISDIR}/connman:"

SRC_URI += " \
    file://0001-connman.service.in-don-t-start-if-nfs-boot.patch \
    file://0002-main.conf-blacklist-rndis-nic-s.patch \
    file://0003-connman-clock-ntp-client-should-not-update-time-time.patch \
"

do_install_append() {
    install -d ${D}${sysconfdir}/connman/
    install -m 0644 ${S}/src/main.conf ${D}${sysconfdir}/connman/
}
