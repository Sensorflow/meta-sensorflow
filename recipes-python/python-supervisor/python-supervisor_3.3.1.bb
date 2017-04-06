SUMMARY = "Supervisor"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SRC_URI[md5sum] = "202f760f9bf4930ec06557bac73e5cf2"
SRC_URI[sha256sum] = "fc3af22e5a7af2f6c3be787acf055c1c17777f5607cd4dc935fe633ab97061fd"


SRC_URI = "file://supervisord.conf file://supervisord.init"



do_install_append() {
	     install -d ${D}${sysconfdir}/supervisor/conf.d
	     install -d ${D}${sysconfdir}/init.d
	     
	     install -m 0644 ${WORKDIR}/supervisor/supervisord.conf ${D}${sysconfdir}
	     install -m 0755 ${WORKDIR}/supervisord.init ${D}${sysconfdir}/init.d/supervisord
	     
}

pkg_postinst_${PN}_append() {
    update-rc.d supervisord defaults 90
}

pkg_postrm_${PN}_append() {
    update-rc.d supervisord remove
}

FILES_${PN} += "${sysconfdir}/supervisor/supervisord.conf"



inherit pypi setuptools

CLEANBROKEN = "1"

PYPI_PACKAGE = "supervisor"

RDEPENDS_${PN} = "python-modules python-distribute python-meld3"


 
