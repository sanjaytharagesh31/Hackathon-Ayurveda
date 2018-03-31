package ericjoseph.com.firebasework;

import android.net.Uri;
import java.net.URL;

/**
 * Created by Eric Joseph on 18-03-2018.
 */

public class Organization {
    public String orgname;
    public String orgemail;
    public String orgimage;
    public String orgcert;
    public long phnum;
    public String Address;

    public Organization(String img, String cert,String name, String mail , String address, long number) {
        orgimage = img;
        orgcert = cert;
        orgname = name;
        orgemail = mail;
        Address = address;
        phnum = number;
    }
}
