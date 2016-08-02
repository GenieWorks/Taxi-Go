package xyz.genieworks.taxigo.utils;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import xyz.genieworks.taxigo.R;

/**
 * Util class to add Drawer to activates of the app
 */
public final class DrawerBuilderUtil {

    //call this method on Activity onCreate to add Drawer to this Activity
    public static void addSideDrawer(final Activity activity) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            // ubasic Firebase user info
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();


            // Create the Current User AccountHeader
            AccountHeader headerResult = new AccountHeaderBuilder()
                    .withActivity(activity)
                    .withHeaderBackground(R.mipmap.drawer_backgound)
                    .addProfiles(
                            new ProfileDrawerItem().withName(name).withEmail(email).withIcon(photoUrl)
                    )
                    .build();

            //add Items to Drawer
            PrimaryDrawerItem homeItem = new PrimaryDrawerItem().withIdentifier(1).withName("Home").withIcon(GoogleMaterial.Icon.gmd_home);
            PrimaryDrawerItem accountItem = new PrimaryDrawerItem().withIdentifier(2).withName("Account").withIcon(GoogleMaterial.Icon.gmd_account_circle);
            PrimaryDrawerItem settingsItem = new PrimaryDrawerItem().withIdentifier(3).withName("Settings").withIcon(GoogleMaterial.Icon.gmd_settings);
            PrimaryDrawerItem logoutItem = new PrimaryDrawerItem().withIdentifier(4).withName("Log Out").withIcon(GoogleMaterial.Icon.gmd_power_settings_new);

             //build the drawer
             Drawer result = new DrawerBuilder()
                    .withActivity(activity)
                    .withAccountHeader(headerResult)
                     .withActionBarDrawerToggle(true)
                     .addDrawerItems(
                             homeItem,
                             new DividerDrawerItem(),
                             accountItem,
                             new DividerDrawerItem(),
                             settingsItem,
                             new DividerDrawerItem(),
                             logoutItem
                     )
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            switch(((int) drawerItem.getIdentifier())){
                                case 1 :
                                    Toast.makeText(activity, "Home", Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:
                                    Toast.makeText(activity, "Account", Toast.LENGTH_SHORT).show();
                                    break;
                                case 3:
                                    Toast.makeText(activity, "Settings", Toast.LENGTH_SHORT).show();
                                    break;
                                case 4:
                                    Toast.makeText(activity, "LogOut", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;

                            }
                            return false;
                        }
                    })
                    .build();
        }
    }
}
