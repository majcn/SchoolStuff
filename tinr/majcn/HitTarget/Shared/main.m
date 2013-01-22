//
//  main.m
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import <UIKit/UIKit.h>

int main(int argc, char *argv[])
{
    [GameHost load];
    NSAutoreleasePool * pool = [[NSAutoreleasePool alloc] init];
    int retVal = UIApplicationMain(argc, argv, @"GameHost", @"HitTarget");
    [pool release];
    return retVal;
}
