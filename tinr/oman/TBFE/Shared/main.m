//
//  main.m
//  TBFE
//
//  Created by Marko Oman on 10/21/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Retronator.Xni.Framework.h"

int main(int argc, char *argv[]) {
    [GameHost load];
    NSAutoreleasePool * pool = [[NSAutoreleasePool alloc] init];
    int retVal = UIApplicationMain(argc, argv, @"GameHost", @"TBFE");
    [pool release];
    return retVal;
}
