package org.keycloak.testsuite.console.page.roles;

import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.testsuite.console.page.AdminConsoleRealm;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author tkyjovsk
 */
public class Roles extends AdminConsoleRealm {

    @FindBy(css = "ul.nav-tabs")
    private RoleTabs tabs;

    public RoleTabs tabs() {
        return tabs;
    }

    public class RoleTabs {

        @FindBy(linkText = "Realm Roles")
        private WebElement realmRolesTab;
        @FindBy(linkText = "Default Roles")
        private WebElement defaultRolesTab;

        public void realmRoles() {
            realmRolesTab.click();
        }

        public void defaultRoles() {
            defaultRolesTab.click();
        }

    }

    public RolesResource rolesResource() {
        return realmResource().roles();
    }

}
