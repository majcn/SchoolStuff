//
//  FpsComponent.h
//  TBFE
//
//  Created by Marko Oman on 11/6/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface FpsComponent : GameComponent {
	int fpsCounter;
	NSTimeInterval secondCounter;
}

@end
