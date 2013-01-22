/*
Napisite gonilnik-modul, v katerega lahko pisemo in iz njega beremo. Pomnilnik naprave naj sestoji najvec iz N_BLK kosov pomnilnika, vsak velikosti BLK_SIZE.
(N_BLK in BLK_SIZE sta lahko tudi parametra, ki ju podamo gonilniku.) Bere in pise naj kvantizirano (po blokih).
Pri vsaki operaciji branja/pisanja izpisite tudi dolzino (len) in odmik (offset), zato da vidimo potek kvantiziranega branja/pisanja.
Pri pisanju naj alocira pomnilnik sproti - po potrebi. Velikosti N_BLK*BLK_SIZE ne sme prekoraciti.
*/

#include<linux/kernel.h>
#include<linux/module.h>
#include<linux/fs.h>
#include<asm/uaccess.h>

#define N_BLK 10
#define BLK_SIZE 100

MODULE_LICENSE("GPL");
char **data;
int size = 0;
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
  if(data)
    kfree(data);
  int i = 0;
  while(data[i])
  {
    kfree(data[i]);
    data[i] = NULL;
    i++;
  }
  return 0;
}
int sprosti(struct inode *inode, struct file *file)
{
  return 0;
}

ssize_t beri(struct file *filp, char *buff, size_t len, loff_t *offset)
{
  if ( *offset >= size)
    return 0;
  if ( len > size - *offset)
    len = size - *offset;
  if ( len > BLK_SIZE)
    len = BLK_SIZE;
  int i = offset / BLK_SIZE;
  if ( copy_to_user( buff, data[i], len) )
    return -EFAULT;
  *offset += len;
  return len;
}

ssize_t pisi(struct file *filp, const char *buff, size_t len, loff_t *offset)
{
  if(!data)
  {
    data = kmalloc(N_BLK*sizeof(char*), GFP_KERNEL);
    memset(data, 0, N_BLK*sizeof(char*));
  }
  int i = offset / BLK_SIZE;
  if(!data[i])
  {
    data[i] = kmalloc(BLK_SIZE*sizeof(char*), GFP_KERNEL);
    memset(data[i], 0, BLK_SIZE*sizeof(char*));
  }
  size = i*BLK_SIZE;
  if ( *offset >= size)
    return 0;
  if ( len > BLK_SIZE)
    len = BLK_SIZE;
  if ( copy_from_user( data[i], buff, len) )
    return -EFAULT;
  *offset += len;
  return len;
}
