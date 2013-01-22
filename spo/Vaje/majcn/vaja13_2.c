/*
Napisite gonilnik-modul, v katerega lahko vpisemo en znak in iz katerega ta znak lahko tudi beremo.
*/

#include<linux/kernel.h>
#include<linux/module.h>
#include<linux/fs.h>
#include<asm/uaccess.h>

MODULE_LICENSE("GPL");
static char msg[10];
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
  return 0;
}
int sprosti(struct inode *inode, struct file *file)
{
  return 0;
}

ssize_t beri(struct file *filp, char *buff, size_t len, loff_t *offset)
{
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
  int size = strlen(buff);
  if ( *offset >= size)
    return 0;
  if ( len > size - *offset)
    len = size - *offset;
  if ( copy_from_user( msg, buff, len) )
    return -EFAULT;
  *offset += len;
  return len;
}
