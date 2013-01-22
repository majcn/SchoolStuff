/*
Napisite gonilnik-modul, ki ob vsakem branju izpise, kateric po vrsti beremo z njega. (Lahko uporabite okostje preprostega modula s prosojnic.)
*/

#include<linux/kernel.h>
#include<linux/module.h>
#include<linux/fs.h>
#include<asm/uaccess.h>

MODULE_LICENSE("GPL");
static int stevec = 0;
int zacetni_modul(void);
void koncni_modul(void);
int odpri(struct inode *, struct file *);
int sprosti(struct inode *, struct file *);
ssize_t beri(struct file *, char *, size_t, loff_t *);
ssize_t pisi(struct file *, const char *, size_t, loff_t *);
#define DEVICE_NAME "okostje"
int Major;

struct file_operations fops = {
  .open = odpri,
  .release = sprosti,
  .read = beri,
  .write = pisi
};
module_init(zacetni_modul);
module_exit(koncni_modul);

int zacetni_modul(void)
{
  Major = register_chrdev(0, DEVICE_NAME, &fops);
  if (Major < 0)
  {
    printk(KERN_ALERT "Registracija znakovne naprave spodletela.\n");
    return Major;
  }
  printk(KERN_INFO "Glavno stevilo je %d.\n", Major);
  return 0;
}

void koncni_modul(void)
{
  unregister_chrdev(Major, DEVICE_NAME);
}

int odpri(struct inode *inode, struct file *file)
{
  stevec++;
  return 0;
}
int sprosti(struct inode *inode, struct file *file)
{
  return 0;
}

ssize_t beri(struct file *filp, char *buff, size_t len, loff_t *offset)
{
  char msg[10];// = "Hello!\n";
  sprintf(msg, "%d\n", stevec);
  int size = strlen(msg);
  if ( *offset >= size)
    return 0;
  if ( len > size - *offset)
    len = size - *offset;
  if ( copy_to_user( buff, msg, len) )
    return -EFAULT;
  *offset += len;
  return len;
}

ssize_t pisi(struct file *filp, const char *buff, size_t len, loff_t *offset)
{
  return 0;
}
