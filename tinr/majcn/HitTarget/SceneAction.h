//
//  SceneAction.h
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef enum {
	SceneOperationAdd,
	SceneOperationRemove
} SceneOperation;

@interface SceneAction : NSObject
{
	SceneOperation operation;
	id item;
}

@property (nonatomic) SceneOperation operation;
@property (nonatomic, retain) id item;

+ (SceneAction*) actionWithOperation:(SceneOperation)theOperation item:(id)theItem;

@end
