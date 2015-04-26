//
//  ViewController.h
//  tic_tac_snow
//
//  Created by Hao Zhou on 2/7/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <AVFoundation/AVFoundation.h>
#import "DrawLine.h"

@interface ViewController : UIViewController
<UIGestureRecognizerDelegate, UIAlertViewDelegate, AVAudioPlayerDelegate>
@property (weak, nonatomic) IBOutlet UIImageView *gridImage;

@property NSMutableArray *gridArray;
@property NSMutableArray *endPoints;
@property UIPanGestureRecognizer *x_Pan;
@property UIPanGestureRecognizer *o_Pan;


@property int step_num;
@property BOOL gameRun;
@property DrawLine *winLine;
@property CGFloat height;
@property CGFloat width;
@property BOOL cellFalling;


@property (weak, nonatomic) IBOutlet UIImageView *x_imageView;
@property (weak, nonatomic) IBOutlet UIImageView *o_imageView;
- (IBAction)helpButton:(id)sender;
@property (weak, nonatomic) IBOutlet UIImageView *cellImage1;
@property (weak, nonatomic) IBOutlet UIImageView *cellImage2;
@property (weak, nonatomic) IBOutlet UIImageView *cellImage3;

@property (weak, nonatomic) IBOutlet UIImageView *cellImage4;
@property (weak, nonatomic) IBOutlet UIImageView *cellImage5;
@property (weak, nonatomic) IBOutlet UIImageView *cellImage6;

@property (weak, nonatomic) IBOutlet UIImageView *cellImage7;
@property (weak, nonatomic) IBOutlet UIImageView *cellImage8;
@property (weak, nonatomic) IBOutlet UIImageView *cellImage9;

@property (weak, nonatomic) NSString *soundPath;
@property (nonatomic) AVAudioPlayer * audio;
@end

